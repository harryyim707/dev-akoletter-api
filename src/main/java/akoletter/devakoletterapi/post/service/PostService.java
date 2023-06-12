package akoletter.devakoletterapi.post.service;

import java.io.IOException;

import akoletter.devakoletterapi.common.member.domain.request.DeleteAccountRequest;
import akoletter.devakoletterapi.post.domain.request.DeletePostRequest;
import org.springframework.http.ResponseEntity;


public interface PostService {

  ResponseEntity<?> getPostDetail(long postId);

  ResponseEntity<?> getPostList(int size, String category);

  ResponseEntity<?> showImage(int fileId) throws IOException;

  ResponseEntity<?> deletePost(DeletePostRequest request);
}
