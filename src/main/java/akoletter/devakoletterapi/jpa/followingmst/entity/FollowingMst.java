package akoletter.devakoletterapi.jpa.followingmst.entity;

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
@Table(name = "following_mst")
@IdClass(FollowingMstPk.class)
public class FollowingMst {
  @Id
  @Column(name = "follow_id")
  @Schema(description = "팔로우 테이블 row num")
  private Long followId;

  @Column(name = "follow_usr_id")
  @Schema(description = "팔로우 하는 usr id")
  private Long followUsrId;

  @Column(name = "fk_unq_usr_id")
  @Schema(description = "팔로잉 하는 대상자, 즉 기준 ID")
  private Long unqUsrId;
}
