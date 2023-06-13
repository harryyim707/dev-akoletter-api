package akoletter.devakoletterapi.jpa.postmst.entity;

import akoletter.devakoletterapi.util.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "post_mst")
@IdClass(PostMstPk.class)
public class PostMst extends BaseEntity {
  @Id
  @Column(name = "post_id")
  @Schema(description = "게시글 id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

  @Column(name = "post_title")
  @Schema(description = "게시글 제목")
  private String postTitle;

  @Column(name = "post_content")
  @Schema(description = "게시글 내용")
  private String postContent;

  @Column(name = "category", length = 10000)
  @Schema(description = "게시글 카테고리")
  private String category;

  @Column(name = "fk_unq_usr_id")
  @Schema(description = "게시글 작성자 id")
  private Long unqUsrId;

  @Column(name = "fk_file_id")
  @Schema(description = "첨부파일 id1")
  private int fileId;

  @Column(name = "fk_file_id2")
  @Schema(description = "첨부파일 id2")
  private int fileId2;

  @Column(name = "fk_file_id3")
  @Schema(description = "첨부파일 id3")
  private int fileId3;
  @Column(name = "url_list")
  @Schema(description = "url")
  private String urlList;


}
