package akoletter.devakoletterapi.post.PostListLoad.controller;

import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.post.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.common.member.service.MemberService;
import akoletter.devakoletterapi.post.PostListLoad.service.PostListLoadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/postlist")
@Tag(name = "post list", description = "게시글 불러오기 콘트롤러")
@Validated
public class PostListLoadController {

    private final PostListLoadService postListLoadService;
    private final MemberService memberService;

    @GetMapping("/recent")
    public ResponseEntity<PostListLoadResponse> postload(@RequestBody PostListLoadRequest request) throws Exception {
        return new ResponseEntity<>(postListLoadService.postload(request), HttpStatus.OK);
    }


}

