package akoletter.devakoletterapi.common.member.domain.response;

import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

  private String usrId;

  private String usrNm;

  private String usrEmail;

  private String usrTelNo;

  private List<Authority> roles = new ArrayList<>();

  private TokenDto token;

  public LoginResponse(MemberMst member) {
    this.usrNm = member.getUsrNm();
    this.usrId = member.getUsrId();
    this.usrEmail = member.getUsrEmail();
    this.usrTelNo = member.getUsrTelNo();
    this.roles = member.getRoles();
  }
}
