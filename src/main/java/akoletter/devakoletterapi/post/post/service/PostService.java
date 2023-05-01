package akoletter.devakoletterapi.post.post.service;

import akoletter.devakoletterapi.post.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.post.domain.response.GetPostDetailResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseEntity<GetPostDetailResponse> postdetailload(GetPostDetailRequest request);

}
