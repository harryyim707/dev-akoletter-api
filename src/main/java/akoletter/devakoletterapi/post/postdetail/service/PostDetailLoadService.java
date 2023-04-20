package akoletter.devakoletterapi.post.postdetail.service;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.post.postdetail.domain.request.PostDetailLoadRequest;
import akoletter.devakoletterapi.post.postdetail.domain.response.PostDetailLoadResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostDetailLoadService {
    ResponseEntity<PostDetailLoadResponse> postdetailload(PostDetailLoadRequest request);

}
