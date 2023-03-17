package akoletter.devakoletterapi.util.base;

import jakarta.persistence.*;
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
public class BaseEntity {
  private long seqNo = 1;
  private String useYn = "Y";
  @CreatedBy
  @Column(updatable = false)
  private String frstRgstId;
  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime frstRgstDt;
  @LastModifiedBy
  private String lastMdfyId;
  @UpdateTimestamp
  private LocalDateTime lastMdfyDt;

  public BaseEntity() {
  }

  @PrePersist
  public void prePersist() {
    try {

    }catch (Exception e){

    }
  }

  @PreUpdate
  public void preUpdate() {
    try {

    }catch (Exception e){
    }
  }

  public BaseEntity(long seqNo, String frstRgstId,
                    LocalDateTime frstRgstDt, String lastMdfyId,
                    LocalDateTime lastMdfyDt) {
    this.seqNo = seqNo;
    this.frstRgstId = frstRgstId;
    this.frstRgstDt = frstRgstDt;
    this.lastMdfyId = lastMdfyId;
    this.lastMdfyDt = lastMdfyDt;
  }
}
