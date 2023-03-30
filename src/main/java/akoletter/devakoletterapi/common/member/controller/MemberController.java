package akoletter.devakoletterapi.common.member.controller;

import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.common.member.domain.response.SignUpResponse;
import akoletter.devakoletterapi.common.member.service.MemberService;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final MemberMstRepository memberMstRepository;


  @PostMapping(value = "/login")
  public ResponseEntity<LoginResponse> signin(@RequestBody LoginRequest request) throws Exception {
    LoginResponse loginResponse = memberService.login(request);
    return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
  }
  @PostMapping(value = "/join")
  public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpRequest request) throws Exception {
    return new ResponseEntity<>(memberService.signUp(request), HttpStatus.OK);
  }

  @GetMapping("/user/get")
  public ResponseEntity<LoginResponse> getUser(@RequestParam String account) throws Exception {
    return new ResponseEntity<>( memberService.getMember(account), HttpStatus.OK);
  }
  @GetMapping("/refresh")
  public ResponseEntity<TokenDto> refresh(@RequestBody TokenDto token) throws Exception {
    return new ResponseEntity<>( memberService.refreshAccessToken(token), HttpStatus.OK);
  }
}
