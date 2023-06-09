package akoletter.devakoletterapi.post.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class GetPostDetailRequest {
    @Schema(
            description = "액세스 토큰",
            hidden = true
    )
    private String accessToken;
}
