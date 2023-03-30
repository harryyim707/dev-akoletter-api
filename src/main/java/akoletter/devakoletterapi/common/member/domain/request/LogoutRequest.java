package akoletter.devakoletterapi.common.member.domain.request;

import lombok.Data;

@Data
public class LogoutRequest {

  private String accessToken;

  private String refreshToken;

}
