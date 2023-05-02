package akoletter.devakoletterapi.post.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostListResponse {

    @Schema(description = "게시글 id")
    private Long postId;

    @Schema(description = "게시글 제목")
    private String postTitle;

    @Schema(description = "게시글 내용")
    private String postContent;

    @Schema(description = "게시글 작성자 id")
    private long unqUsrId;

    @Schema(description = "첨부파일 id")
    private int fileId;



    public GetPostListResponse(PostMst post){
        this.postId=post.getPostId();
        this.postContent=post.getPostContent();
        this.postTitle=post.getPostTitle();
        this.unqUsrId=post.getUnqUsrId();
        this.fileId=post.getFileId();
    }
}
