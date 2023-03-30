package akoletter.devakoletterapi.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class TokenDto {
  private String access_token;
  private String refresh_token;

  private Integer expiration_ms;
}
