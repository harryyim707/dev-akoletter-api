package akoletter.devakoletterapi.PostListLoad.controller;

import akoletter.devakoletterapi.PostListLoad.domain.request.PostListLoadRequest;
import akoletter.devakoletterapi.PostListLoad.domain.response.PostListLoadResponse;
import akoletter.devakoletterapi.common.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import akoletter.devakoletterapi.PostListLoad.service.PostListLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/postlist")
@Tag(name = "post list", description = "게시글 불러오기 콘트롤러")
@Validated
public class PostListLoadController {

    private final PostListLoadService postListLoadService;
    @Operation(summary = "글 불러오기 API", responses = {
            @ApiResponse(responseCode = "200"
                    , description = "성공"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = PostListLoadResponse.class)))
    })
    @GetMapping("/recent")
    public List<PostListLoadResponse> postload(@Valid @RequestBody PostListLoadRequest request){
        return postListLoadService.postload(request);
    }
}

