package akoletter.devakoletterapi.jpa.likemst.entity;

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
@Table(name = "like_mst")
@IdClass(LikeMstPk.class)
public class LikeMst {

  @Id
  @Column(name = "like_id")
  @Schema(description = "좋아요 테이블 row num")
  private Long likeId;

  @Column(name = "fk_unq_usr_id")
  @Schema(description = "좋아요 누른 사람의 id")
  private String unqUsrId;

  @Column(name = "fk_post_id")
  @Schema(description = "해당 게시글 id")
  private Long postId;
}
