package akoletter.devakoletterapi.jpa.likemst.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeMstPk implements Serializable {
  private static final long serialVersionUID = -3707559740976324731L;
  private Long likeId;
}
