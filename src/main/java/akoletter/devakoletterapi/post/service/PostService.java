package akoletter.devakoletterapi.post.service;

import java.io.IOException;
import org.springframework.http.ResponseEntity;

public interface PostService {

  ResponseEntity<?> getPostDetail(long postId);

  ResponseEntity<?> getPostList(String category);

  ResponseEntity<?> showImage(int fileId) throws IOException;

}
