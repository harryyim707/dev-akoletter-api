package akoletter.devakoletterapi.jpa.followingmst.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowingMstPk implements Serializable {
  private static final long serialVersionUID = -3707559740976324731L;
  private Long followId;
}
