package akoletter.devakoletterapi.post.controller;

import akoletter.devakoletterapi.post.domain.request.SavePostRequest;
import akoletter.devakoletterapi.post.domain.response.GetPostListResponse;
import akoletter.devakoletterapi.post.domain.response.SavePostResponse;
import akoletter.devakoletterapi.post.domain.request.GetPostListRequest;
import akoletter.devakoletterapi.post.domain.request.GetPostDetailRequest;
import akoletter.devakoletterapi.post.domain.response.GetPostDetailResponse;
import akoletter.devakoletterapi.post.service.PostService;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import akoletter.devakoletterapi.util.File.service.FileService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RequestMapping("/post")
@Tag(name = "post", description = "게시글 콘트롤러")
@Validated
public class PostController {
    private final PostService postService;
    private final Response response;

    private final FileService fileService;

    @GetMapping("/getpostdetail/{id}")
    public ResponseEntity<GetPostDetailResponse> getPostDetail(@RequestBody GetPostDetailRequest request, Errors errors) {
        if(errors.hasErrors()){
            return (ResponseEntity<GetPostDetailResponse>) response.invalidFields(Helper.refineErrors(errors));
        }
        return postService.getPostDetail(request);
    }
    @GetMapping("/postlist")
    public ResponseEntity<List<GetPostListResponse>> getPostList() {

        return postService.getPostList();
    }

    @PostMapping("/savepost")
    public ResponseEntity<SavePostResponse> savePost(@RequestPart (value ="request") SavePostRequest request,
                                                     @RequestPart(value ="files", required=false) List<MultipartFile> files)throws Exception{
            return postService.savePost(request,files);
    }


}
