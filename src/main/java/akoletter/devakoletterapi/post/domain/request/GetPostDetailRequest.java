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
            description = "게시글 아이디",
            hidden = true
    )
    private long postId;
}
