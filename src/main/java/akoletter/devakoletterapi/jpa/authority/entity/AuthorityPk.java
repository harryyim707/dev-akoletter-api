package akoletter.devakoletterapi.jpa.authority.entity;

import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityPk implements Serializable {
  private static final long serialVersionUID = -3707559740976324731L;
  @Column(name = "auth_id")
  private Long authId;

}
