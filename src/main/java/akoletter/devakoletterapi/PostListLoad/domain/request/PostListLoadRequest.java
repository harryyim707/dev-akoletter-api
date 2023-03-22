package akoletter.devakoletterapi.PostListLoad.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class PostListLoadRequest {

    @Schema(
            description = "불러올 게시글 수",
            hidden = true
    )
    private long postNm;
}
