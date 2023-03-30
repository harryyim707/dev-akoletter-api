package akoletter.devakoletterapi.jpa.codemst.entity;

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
@Table(name = "code_mst")
@IdClass(CodeMstPk.class)
public class CodeMst {
  @Id
  @Column(name = "cd_grp")
  @Schema(description = "코드 그룹")
  private Long cdGrp;

  @Column(name = "cd_grp_nm")
  @Schema(description = "코드 그룹 이름")
  private String cdGrpNm;

  @Column(name = "cd_grp_desc")
  @Schema(description = "코드 그룹 설명")
  private String cdGrpDesc;
}
