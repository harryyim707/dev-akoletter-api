package akoletter.devakoletterapi.post.controller;

import akoletter.devakoletterapi.post.domain.request.GetImageRequest;
import akoletter.devakoletterapi.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.domain.request.GetPostListRequest;
import akoletter.devakoletterapi.post.service.PostService;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "post", description = "게시글 콘트롤러")
@Validated
public class PostController {

    private final PostService postService;
    private final Response response;


    @GetMapping("/getpost/{id}")
    public ResponseEntity<?> getPostDetail(@PathVariable("id") long postId) {

        return postService.getPostDetail(postId);
    }

    @GetMapping("/getpostlist/{category}")
    public ResponseEntity<?> getPostList(@PathVariable("category") String category) {

        return postService.getPostList(category);
    }

    @GetMapping(value = "/images/{fileId}")
    public ResponseEntity<?> showImage(@PathVariable("fileId") int fileId) throws IOException{
       
        return postService.showImage(fileId);
    }
}
