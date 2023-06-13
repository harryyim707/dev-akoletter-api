package akoletter.devakoletterapi.editor.domain.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SavePostRequest {
    @Schema(description = "게시글 제목")
    private String postTitle;

    @Schema(description = "게시글 내용")
    private String postContent;

    @Schema(description = "게시글 카테고리")
    private String category;

    @Schema(description = "게시글 작성자 id")
    private String usrId;

    @Schema(description = "참고 url")
    private String urlList;






}
