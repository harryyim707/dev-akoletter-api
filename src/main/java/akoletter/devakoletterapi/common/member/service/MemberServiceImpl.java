package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.SignUpResponse;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
  private final MemberMstRepository memberMstRepository;
  private final PasswordEncoder passwordEncoder;

  public SignUpResponse signUp(SignUpRequest request){
    MemberMst memberMst = memberMstRepository.findByUsrId(request.getUsrId());
    SignUpResponse result = new SignUpResponse();
    MemberMst member = new MemberMst();
    LocalDateTime now = LocalDateTime.now();
    if(memberMst == null){
      member.setUnqUsrId(passwordEncoder.encode(request.getUsrId()));
      member.setUsrId(request.getUsrId());
      member.setUsrPwd(passwordEncoder.encode(request.getUsrPwd()));
      member.setUsrNm(request.getUsrNm());
      member.setUsrEmail(request.getUsrEmail());
      member.setUsrTelNo(request.getUsrTelNo());
      memberMstRepository.save(member);
      result.setSuccess(true);
    }
    else result.setSuccess(false);
    return result;
  }

}
