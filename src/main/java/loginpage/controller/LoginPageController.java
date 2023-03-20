package loginpage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import loginpage.domain.request.LoginUserRequest;
import loginpage.domain.response.LoginUserResponse;
import loginpage.service.LoginPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/Loginpage")
@Tag(name = "akoletter login page", description = "첫 화면 관련 콘트롤러.")
@Validated
public class LoginPageController {

    @GetMapping("/{usrid}")
    @Operation(summary = "회원 정보 조회 API", responses = {
            @ApiResponse(responseCode = "200"
                    , description = "성공"
                    , content = @Content(mediaType = "application/json"
                    , schema = @Schema(implementation = LoginUserResponse.class)))
    })
    public @ResponseBody
    ResultModel<LoginUserResponse> getUsrInfo(LoginUserRequest loginUserRequest,
                                                 @PathVariable("usrid") @NotBlank String usrid) {
        loginUserRequest.setBkIsbn(usrid);
        return LoginPageService.getUsrInfo(loginUserRequest);
    }
}
