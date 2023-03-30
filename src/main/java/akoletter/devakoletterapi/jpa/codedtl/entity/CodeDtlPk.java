package akoletter.devakoletterapi.jpa.codedtl.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDtlPk implements Serializable {
  private static final long serialVersionUID = -3707559740976324731L;
  private Long cdId;
  private Long cdGrp;
}
