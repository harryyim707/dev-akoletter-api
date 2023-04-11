package akoletter.devakoletterapi.util.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
  @Column(name = "seq_no")
  private Integer seqNo = 1;

  @Column(name = "use_yn")
  private String useYn = "Y";
  @CreatedBy
  @Column(updatable = false, name = "frst_rgst_id")
  private String frstRgstId;
  @CreationTimestamp
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(updatable = false, name = "frst_rgst_dt")
  private LocalDateTime frstRgstDt;
  @LastModifiedBy
  @Column(name = "last_mdfy_id")
  private String lastMdfyId;
  @UpdateTimestamp
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(name = "last_mdfy_dt")
  private LocalDateTime lastMdfyDt;

  public BaseEntity() {
  }

  public BaseEntity(Integer seqNo, String frstRgstId,
                    LocalDateTime frstRgstDt, String lastMdfyId,
                    LocalDateTime lastMdfyDt) {
    this.seqNo = seqNo;
    this.frstRgstId = frstRgstId;
    this.frstRgstDt = frstRgstDt;
    this.lastMdfyId = lastMdfyId;
    this.lastMdfyDt = lastMdfyDt;
  }
}
