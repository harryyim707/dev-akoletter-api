package akoletter.devakoletterapi.post.post.controller;

import akoletter.devakoletterapi.post.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.post.post.service.PostService;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "post", description = "게시글 콘트롤러")
@Validated
public class PostController {
    private final PostService postService;
    private final Response response;

    @GetMapping("/getpostdetail/{id}")
    public ResponseEntity<GetPostDetailResponse> getPostDetail(@RequestBody GetPostDetailRequest request, Errors errors) {
        if(errors.hasErrors()){
            return (ResponseEntity<GetPostDetailResponse>) response.invalidFields(Helper.refineErrors(errors));
        }
        return postService.postdetailload(request);
    }
}
