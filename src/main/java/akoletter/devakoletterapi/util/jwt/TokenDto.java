package akoletter.devakoletterapi.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class TokenDto {
  private String accessToken;
  private String refreshToken;

  private Long accessTokenExpirationTime;

  private Integer refreshTokenExpirationTime;
}
