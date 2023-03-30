package akoletter.devakoletterapi.jpa.membersub.entity;

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
@Table(name = "member_sub")
@IdClass(MemberSubPk.class)
@Schema(description = "프로필 정보를 담은 테이블")
public class MemberSub {
  @Id
  @Column(name = "fk_unq_usr_id")
  @Schema(description = "사용자 고유 id")
  private Long unqUsrId;

  @Column(name = "fk_file_id")
  @Schema(description = "프로필 사진 이미지 파일 id")
  private Long fileId;

  @Column(name = "intro_comment")
  @Schema(description = "자기 소개")
  private String introComment;

  @Column(name = "usr_nickname")
  @Schema(description = "사용자 닉네임")
  private String usrNickname;

}
