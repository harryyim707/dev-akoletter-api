package akoletter.devakoletterapi.jpa.filemst.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "file_mst")
@IdClass(FileMstPk.class)
public class FileMst {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "file_id")
  @Schema(description = "파일 ID")
  private Long fileId;


  @Column(name = "file_seq_no")
  @Schema(description = "파일의 시퀀스 번호")
  private long fileSeqNo;

  @Column(name = "ref_tbl")
  @Schema(description = "참조하는 테이블 이름")
  private String refTbl;

  @Column(name = "file_path")
  @Schema(description = "파일 경로")
  private String filePath;

  @Column(name = "org_file_nm")
  @Schema(description = "파일 원본 이름")
  private String orgFileNm;

  @Column(name = "file_ext")
  @Schema(description = "파일 확장자")
  private String fileExt;

  @Column(name = "file_size")
  @Schema(description = "파일 크기")
  private Long fileSize;

  @Column(name = "file_type")
  @Schema(description = "파일 종류")
  private String fileType;
}
