package akoletter.devakoletterapi.common.auth.controller;

import akoletter.devakoletterapi.common.auth.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.auth.domain.response.SignUpResponse;
import akoletter.devakoletterapi.common.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/join")
  public SignUpResponse signUp(@Valid @RequestBody SignUpRequest request){
    return authService.signUp(request);
  }
}
