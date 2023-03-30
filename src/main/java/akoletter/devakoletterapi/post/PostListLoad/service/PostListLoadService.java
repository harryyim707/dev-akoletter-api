package akoletter.devakoletterapi.post.PostListLoad.service;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import org.springframework.http.ResponseEntity;

public interface PostListLoadService {

  ResponseEntity<?> postload(PostListLoadRequest request);

}
