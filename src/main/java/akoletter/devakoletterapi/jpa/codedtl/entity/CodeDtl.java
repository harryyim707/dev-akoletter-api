package akoletter.devakoletterapi.jpa.codedtl.entity;

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
@Table(name = "code_dtl")
@IdClass(CodeDtlPk.class)
public class CodeDtl {
  @Id
  @Column(name = "cd_id")
  @Schema(description = "코드 ID")
  private String cdId;

  @Id
  @Column(name = "fk_cd_grp")
  @Schema(description = "코드 그룹")
  private String cdGrp;

  @Column(name = "cd_nm")
  @Schema(description = "코드 이름")
  private String cdNm;

  @Column(name = "cd_value")
  @Schema(description = "코드 값")
  private String cdValue;

  @Column(name = "cd_desc")
  @Schema(description = "코드 설명")
  private String cdDesc;

}
