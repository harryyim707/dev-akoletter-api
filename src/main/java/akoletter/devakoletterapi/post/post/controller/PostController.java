package akoletter.devakoletterapi.post.postdetail.controller;

import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.post.postdetail.domain.request.PostDetailLoadRequest;
import akoletter.devakoletterapi.post.postdetail.domain.response.PostDetailLoadResponse;
import akoletter.devakoletterapi.post.postdetail.service.PostDetailLoadService;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "post detail", description = "상세 게시글 불러오기 콘트롤러")
@Validated
public class PostDetailLoadController {
    private final PostDetailLoadService postDetailLoadService;
    private final Response response;

    @GetMapping("/postdetail")
    public ResponseEntity<PostDetailLoadResponse> postdetailload(@RequestBody PostDetailLoadRequest request, Errors errors) {
        if(errors.hasErrors()){
            return (ResponseEntity<PostDetailLoadResponse>) response.invalidFields(Helper.refineErrors(errors));
        }
        return postDetailLoadService.postdetailload(request);
    }
}
