package akoletter.devakoletterapi.common.member.domain.request;

import lombok.Data;

@Data
public class LoginRequest {
  private String usrId;
  private String usrPwd;
}
