package akoletter.devakoletterapi.jpa.commentmst.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "comment_mst")
@IdClass(CommentMstPk.class)
public class CommentMst {
  @Id
  @Column(name = "comment_id")
  @Schema(description = "댓글 ID")
  private Long commentId;

  @Column(name = "comment_content")
  @Schema(description = "댓글 내용")
  private String commentContent;

  @Column(name = "fk_post_id")
  @Schema(description = "게시글 ID")
  private Long postId;

  @Column(name = "fk_unq_usr_id")
  @Schema(description = "댓글 작성자 ID")
  private Long unqUsrId;
}
