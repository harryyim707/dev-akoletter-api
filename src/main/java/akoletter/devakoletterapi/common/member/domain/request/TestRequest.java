package akoletter.devakoletterapi.common.member.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class TestRequest {

  private String token;
  private String requestId;
}
