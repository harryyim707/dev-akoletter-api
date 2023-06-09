package akoletter.devakoletterapi.jpa.postmst.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMstPk implements Serializable {
  private static final long serialVersionUID = -3707559740976324731L;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;
}
