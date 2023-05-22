package akoletter.devakoletterapi.post.domain.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostListDomain {
  private Long postId;

  private String postTitle;

  private String usrId;

  private int fileId;

  private String date;
}
