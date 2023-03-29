package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.common.member.domain.response.SignUpResponse;
import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.token.entity.Token;
import akoletter.devakoletterapi.jpa.token.repo.TokenRepository;
import akoletter.devakoletterapi.util.jwt.JwtProvider;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.util.Collections;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
  private final MemberMstRepository memberMstRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;
  private final TokenRepository tokenRepository;

  private final Integer EXP = Math.toIntExact(Duration.ofDays(14).toMillis());
//  private final Integer EXP = 1000 * 60;

  @Override
  public LoginResponse login(LoginRequest request) throws Exception {
    MemberMst memberMst = memberMstRepository.findByUsrId(request.getUsrId()).orElseThrow(
        () -> new BadCredentialsException("잘못된 계정정보입니다.")
    );
    if (!passwordEncoder.matches(request.getUsrPwd(), memberMst.getUsrPwd())){
      throw new BadCredentialsException("잘못된 계정정보입니다.");
    }
    if(memberMst.getRefreshToken() == null){
      memberMst.setRefreshToken(createRefreshToken(memberMst));
      memberMstRepository.save(memberMst);
    }
    return LoginResponse.builder()
        .usrId(memberMst.getUsrId())
        .usrNm(memberMst.getUsrNm())
        .usrEmail(memberMst.getUsrEmail())
        .usrTelNo(memberMst.getUsrTelNo())
        .roles(memberMst.getRoles())
        .token(TokenDto.builder()
            .access_token(jwtProvider.createToken(memberMst.getUsrId(), memberMst.getRoles()))
            .refresh_token(memberMst.getRefreshToken())
            .build())
        .build();
  }

  @Override
  public SignUpResponse signUp(SignUpRequest request) throws Exception{
    SignUpResponse result = new SignUpResponse();
    result.setSuccess(false);
    try {
      MemberMst memberMst = MemberMst.builder()
          .usrId(request.getUsrId())
          .usrPwd(passwordEncoder.encode(request.getUsrPwd()))
          .usrNm(request.getUsrNm())
          .usrEmail(request.getUsrEmail())
          .usrTelNo(request.getUsrTelNo())
          .build();

      memberMst.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
      memberMstRepository.save(memberMst);
      result.setSuccess(true);
    } catch (Exception e) {
      throw e;
    }
    return result;
  }

  @Override
  public LoginResponse getMember(String account) throws Exception {
    MemberMst memberMst = memberMstRepository.findByUsrId(account)
        .orElseThrow(()->new Exception("계정을 찾을 수 없습니다."));
    return new LoginResponse(memberMst);
  }

  // Refresh Token

  /**
   * Refresh 토큰을 생성한다.
   * Redis 내부에는
   * refreshToken:memberId : tokenValue
   * 형태로 저장한다.
   */
  @Override
  public String createRefreshToken(MemberMst memberMst){
    Token token = tokenRepository.save(
        Token.builder()
            .id(memberMst.getUnqUsrId())
            .refresh_token(UUID.randomUUID().toString())
            .expiration(EXP)
            .build()
    );
    return token.getRefresh_token();
  }
  @Override
  public Token validRefreshToken(MemberMst memberMst, String refreshToken) throws Exception {
    Token token = tokenRepository.findById(memberMst.getUnqUsrId())
        .orElseThrow(() -> new Exception("만료된 계정입니다. 로그인을 다시 시도하세요."));
    // 해당유저의 Refresh 토큰 만료 : Redis에 해당 유저의 토큰이 존재하지 않음
    if (token.getRefresh_token() == null) {
      return null;
    } else {
      // 리프레시 토큰 만료일자가 얼마 남지 않았을 때 만료시간 연장..?
      if(token.getExpiration() < 10) {
        token.setExpiration(1000);
        tokenRepository.save(token);
      }

      // 토큰이 같은지 비교
      if(!token.getRefresh_token().equals(refreshToken)) {
        return null;
      } else {
        return token;
      }
    }
  }
  @Override
  public TokenDto refreshAccessToken(TokenDto token) throws Exception {
    String usrId = jwtProvider.getAccount(token.getAccess_token());
    MemberMst memberMst = memberMstRepository.findByUsrId(usrId).orElseThrow(
        () -> new BadCredentialsException("잘못된 계정정보입니다."));
    Token refreshToken = validRefreshToken(memberMst, token.getRefresh_token());
    if (refreshToken != null) {
      return TokenDto.builder()
          .access_token(jwtProvider.createToken(usrId, memberMst.getRoles()))
          .refresh_token(memberMst.getRefreshToken())
          .build();
    } else {
      throw new Exception("로그인을 해주세요");
    }
  }
}
