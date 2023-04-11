package akoletter.devakoletterapi.post.PostListLoad.service;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostListLoadService {

  ResponseEntity<List<PostListLoadResponse>> postload(PostListLoadRequest request);

}
