package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.DeleteAccountRequest;
import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.LogoutRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.authority.repo.AuthorityRepository;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.token.entity.Token;
import akoletter.devakoletterapi.jpa.token.repo.TokenRepository;
import akoletter.devakoletterapi.util.jwt.JwtProvider;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import akoletter.devakoletterapi.util.response.Response;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

  private final Response response;
  private final MemberMstRepository memberMstRepository;
  private final AuthorityRepository authorityRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final TokenRepository tokenRepository;

  private final RedisTemplate<String, String> redisTemplate;

  private final Integer EXP = Math.toIntExact(Duration.ofDays(14).toMillis());
//  private final Integer EXP = 1000 * 60 * 2;

  @Override
  @Transactional
  public ResponseEntity<?> login(LoginRequest request) {
    MemberMst memberMst = memberMstRepository.findByUsrId(request.getUsrId()).orElse(null);
    if (memberMst == null) {
      return response.fail("계정 정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
    if ("N".equals(memberMst.getUseYn())) {
      return response.fail("탈퇴한 사용자입니다.", HttpStatus.BAD_REQUEST);
    }
    if (!passwordEncoder.matches(request.getUsrPwd(), memberMst.getUsrPwd())) {
      return response.fail("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
    TokenDto tokenDto = jwtProvider.createAccessToken(memberMst.getUsrId(), memberMst.getRoles());
    Token refreshToken = validRefreshToken(memberMst, memberMst.getRefreshToken());
    if (refreshToken == null || refreshToken.getExpiration() <= 0) {
      refreshToken = createRefreshToken(memberMst);
      memberMst.setRefreshToken(refreshToken.getRefreshToken());
    }
    memberMstRepository.save(memberMst);
//    List<Authority> roles = authorityRepository.findAllByMember(memberMst.getUnqUsrId());
    tokenDto.setRefreshToken(refreshToken.getRefreshToken());
    tokenDto.setRefreshTokenExpirationTime(refreshToken.getExpiration());
    LoginResponse loginResponse = LoginResponse.builder()
        .usrId(memberMst.getUsrId())
        .usrNm(memberMst.getUsrNm())
        .usrEmail(memberMst.getUsrEmail())
        .usrTelNo(memberMst.getUsrTelNo())
        .roles(memberMst.getRoles())
        .token(tokenDto)
        .build();
    return response.success(loginResponse, "로그인에 성공했습니다.", HttpStatus.OK);
  }

  @Override
  @Transactional
  public MemberMst signUp(SignUpRequest request) {
    MemberMst memberCheck = memberMstRepository.findByUsrId(request.getUsrId()).orElse(null);
    if (memberCheck != null
        || memberMstRepository.findByUsrEmail(request.getUsrEmail()).orElse(null) != null
        || memberMstRepository.findByUsrTelNo(request.getUsrTelNo()).orElse(null) != null) {
      return null;
    }
    MemberMst last = memberMstRepository.findTopByOrderByUnqUsrIdDesc().orElse(null);
    Authority lastAuthority = authorityRepository.findTopByOrderByAuthIdDesc().orElse(null);
    Long id = 0L;
    Long authId = 0L;
    if (last == null) {
      id = 1L;
    } else {
      id = last.getUnqUsrId() + 1;
    }
    MemberMst memberMst = new MemberMst();
    memberMst.setUnqUsrId(id);
    memberMst.setUsrId(request.getUsrId());
    memberMst.setUsrPwd(passwordEncoder.encode(request.getUsrPwd()));
    memberMst.setUsrNm(request.getUsrNm());
    memberMst.setUsrEmail(request.getUsrEmail());
    memberMst.setUsrTelNo(request.getUsrTelNo());
    memberMstRepository.saveAndFlush(memberMst);
    if (lastAuthority == null) {
      authId = 1L;
    } else {
      authId = lastAuthority.getAuthId() + 1;
    }
    Authority authority = new Authority();
    authority.setAuthId(authId);
    authority.setName("ROLE_USER");
    authority.setMember(memberMst);
    List<Authority> authorities = new ArrayList<>();
    authorities.add(authority);
    authorityRepository.saveAllAndFlush(authorities);

    return memberMst;
  }
  // Refresh Token

  /**
   * Refresh 토큰을 생성한다. Redis 내부에는 refreshToken:memberId : tokenValue 형태로 저장한다.
   */

  public Token createRefreshToken(MemberMst memberMst) {
    return tokenRepository.save(
        Token.builder()
            .id(memberMst.getUnqUsrId())
            .refreshToken(UUID.randomUUID().toString())
            .expiration(EXP)
            .build()
    );
  }


  public Token validRefreshToken(MemberMst memberMst, String refreshToken) {
    Token token = tokenRepository.findById(memberMst.getUnqUsrId()).orElse(null);
    // 해당유저의 Refresh 토큰 만료 : Redis에 해당 유저의 토큰이 존재하지 않음
    if (token == null || token.getRefreshToken() == null) {
      return null;
    } else {
      // 리프레시 토큰 만료일자가 얼마 남지 않았을 때 null 반환
      if (token.getExpiration() < 10) {
        return null;
      }
      // 토큰이 같은지 비교
      if (!token.getRefreshToken().equals(refreshToken)) {
        return null;
      } else {
        return token;
      }
    }
  }

  @Override
  public ResponseEntity<?> refreshAccessToken(TokenDto token) {
    String usrId = jwtProvider.getAccount(token.getAccessToken());
    MemberMst memberMst = memberMstRepository.findByUsrId(usrId).orElse(null);
    if (memberMst == null) {
      return response.fail("회원정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
    Token refreshToken = validRefreshToken(memberMst, token.getRefreshToken());
    if (refreshToken == null || !memberMst.getRefreshToken()
        .equals(refreshToken.getRefreshToken())) {
      return response.fail("다시 로그인해 주세요.", HttpStatus.BAD_REQUEST);
    }
    TokenDto tokenDto = jwtProvider.createAccessToken(usrId, memberMst.getRoles());
    tokenDto.setRefreshToken(refreshToken.getRefreshToken());
    tokenDto.setRefreshTokenExpirationTime(refreshToken.getExpiration());
    return response.success(tokenDto, "", HttpStatus.OK);
  }

  @Override
  public ResponseEntity<?> delete(DeleteAccountRequest request) {
    String usrId = jwtProvider.getAccount(request.getAccessToken());
    if (usrId == null) {
      return response.fail("다시 로그인해 주십시오.", HttpStatus.BAD_REQUEST);
    }
    MemberMst memberMst = memberMstRepository.findByUsrId(usrId).orElse(null);
    if (memberMst == null) {
      return response.fail("회원정보가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
    memberMst.setUseYn("N");
    List<Authority> authorities = memberMst.getRoles();
    for (Authority authority : authorities) {
      authority.setUseYn("N");
      authority.setName("DELETED");
    }
    memberMst.setRoles(authorities);
    memberMstRepository.save(memberMst);
    return response.success("회원 탈퇴가 완료되었습니다.");
  }


  @Override
  public ResponseEntity<?> logout(LogoutRequest request) {
    String usrId = jwtProvider.getAccount(request.getAccessToken());
    MemberMst memberMst = memberMstRepository.findByUsrId(usrId).orElse(null);
    // access token이 유효한지 확인
    if (!jwtProvider.validateToken(request.getAccessToken()) || usrId == null) {
      return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
    }
    // refresh token 존재 여부 확인
    Token refresh = tokenRepository.findById(memberMst.getUnqUsrId()).orElse(null);
    if (refresh == null) {
      return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
    }
    /** refresh token 삭제
     * 다시 로그인 하면 refresh token 재발급
     * 로그인 하지 않고 사이트를 나가게 되면
     * 1. access token이 유효하다면, 다시 서비스 이용 가능
     * 2. access token이 유효하지 않지만, refresh token이 유효하다면, access token 재발급
     */
    tokenRepository.delete(refresh);
    Long expiration = jwtProvider.getExpiration(request.getAccessToken());
    redisTemplate.opsForValue()
        .set(request.getAccessToken(), "logout", expiration, TimeUnit.MILLISECONDS);
    return response.success("로그아웃 되었습니다.");
  }

}
