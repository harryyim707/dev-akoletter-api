package akoletter.devakoletterapi.post.service;

import akoletter.devakoletterapi.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.domain.request.GetPostListRequest;
import akoletter.devakoletterapi.post.domain.request.SavePostRequest;
import akoletter.devakoletterapi.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.post.domain.response.GetPostListResponse;
import akoletter.devakoletterapi.post.domain.response.SavePostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    ResponseEntity<GetPostDetailResponse> getPostDetail(GetPostDetailRequest request);
    ResponseEntity<List<GetPostListResponse>> getPostList(GetPostListRequest request);
    ResponseEntity<SavePostResponse> savePost(SavePostRequest request, List<MultipartFile> files) throws Exception;


}
