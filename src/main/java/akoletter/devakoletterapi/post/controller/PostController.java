package akoletter.devakoletterapi.post.controller;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.repo.PostMstRepository;
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
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import akoletter.devakoletterapi.util.File.service.FileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*", allowedHeaders = "*")
@Tag(name = "post", description = "게시글 콘트롤러")
@Validated
public class PostController {
    private final PostService postService;
    private final Response response;
    private final MemberMstRepository memberMstRepository;
    private final PostMstRepository postMstRepository;

    @PostMapping("/main/getpost")
    public ResponseEntity<GetPostDetailResponse> getPostDetail(@RequestBody GetPostDetailRequest request, Errors errors) {
        if(errors.hasErrors()){
            return (ResponseEntity<GetPostDetailResponse>) response.invalidFields(Helper.refineErrors(errors));
        }
        return postService.getPostDetail(request);
    }
    @PostMapping("/getpostlist")
    @CrossOrigin(origins="*", allowedHeaders = "*")
    public ResponseEntity<?> getPostList(@RequestBody GetPostListRequest request, Errors errors) {
        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return postService.getPostList(request);
    }

    // TODO: EditorController로 이동시켜야 함
    @PostMapping("/savepost")
    public ResponseEntity<?> savePost(@RequestPart (value ="request") SavePostRequest request,
                                                     @RequestPart(value ="files", required=false) List<MultipartFile> files, Errors errors)throws Exception{
        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }
        SavePostResponse result = postService.savePost(request,files);
        if("exists".equals(result.getSuccess())){
            return response.fail("이미 존재하는 제목입니다.", HttpStatus.BAD_REQUEST);
        }
        MemberMst member = memberMstRepository.findByUsrId(request.getUsrId()).orElse(null);
        PostMst res = postMstRepository.findByPostTitleAndUnqUsrId(request.getPostTitle(), member.getUnqUsrId()).orElse(null);
        return response.success(res, "저장에 성공했습니다." ,HttpStatus.OK);
    }


}
