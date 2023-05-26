package akoletter.devakoletterapi.post.service;

import akoletter.devakoletterapi.post.domain.request.GetPostListRequest;
import java.io.IOException;
import org.springframework.http.ResponseEntity;

public interface PostService {

  ResponseEntity<?> getPostDetail(long postId);

  ResponseEntity<?> getPostList(GetPostListRequest request, String category);

  ResponseEntity<?> showImage(int fileId) throws IOException;

}
