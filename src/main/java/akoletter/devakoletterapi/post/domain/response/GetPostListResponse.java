package akoletter.devakoletterapi.post.domain.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPostListResponse {
    List<PostListDomain> postList;
}
