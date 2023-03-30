package akoletter.devakoletterapi.jpa.token.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@RedisHash("refreshToken")
@Builder @NoArgsConstructor @AllArgsConstructor
public class Token {
  @Id
  @JsonIgnore
  private Long id;

  private String refreshToken;

  @TimeToLive(unit = TimeUnit.MILLISECONDS)
  private Integer expiration;

  public void setExpiration(Integer expiration){
    this.expiration = expiration;
  }
}
