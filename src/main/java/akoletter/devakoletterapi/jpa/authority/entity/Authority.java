package akoletter.devakoletterapi.jpa.authority.entity;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "authority")
@EqualsAndHashCode(callSuper = false)
@IdClass(AuthorityPk.class)
public class Authority extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  @Column(name = "auth_id")
  private Long authId;

  @Column(name = "name")
  private String name;

  @Column(name = "usr_id")
  private String usrId;

  @JoinColumn(name = "unq_usr_id", nullable = false)
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JsonIgnore
  private MemberMst member;

  public void setMember(MemberMst memberMst) {
    this.member = memberMst;
  }
}

