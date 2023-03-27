package akoletter.devakoletterapi.common.member.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
  private String usrId;
  private String usrPwd;
  private String usrNm;
  private String usrEmail;
  private String usrTelNo;
}
