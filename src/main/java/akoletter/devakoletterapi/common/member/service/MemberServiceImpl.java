package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.common.member.domain.response.SignUpResponse;
import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.util.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import java.util.Collections;
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

  @Override
  public LoginResponse login(LoginRequest request) throws Exception {
    MemberMst memberMst = memberMstRepository.findByUsrId(request.getUsrId()).orElseThrow(
        () -> new BadCredentialsException("잘못된 계정정보입니다.")
    );
    if (!passwordEncoder.matches(request.getUsrPwd(), memberMst.getUsrPwd())){
      throw new BadCredentialsException("잘못된 계정정보입니다.");
    }
    return LoginResponse.builder()
        .usrId(memberMst.getUsrId())
        .usrNm(memberMst.getUsrNm())
        .usrEmail(memberMst.getUsrEmail())
        .usrTelNo(memberMst.getUsrTelNo())
        .roles(memberMst.getRoles())
        .token(jwtProvider.createToken(memberMst.getUsrId(), memberMst.getRoles()))
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

}
