package akoletter.devakoletterapi.common.member.controller;

import akoletter.devakoletterapi.common.member.domain.request.DeleteAccountRequest;
import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.LogoutRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.request.TestRequest;
import akoletter.devakoletterapi.common.member.service.MemberService;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import akoletter.devakoletterapi.util.response.Helper;
import akoletter.devakoletterapi.util.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  private final Response response;


  @PostMapping(value = "/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request, Errors errors) {
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return memberService.login(request);
  }
  @PostMapping(value = "/join")
  public ResponseEntity<?> signup(@RequestBody SignUpRequest request, Errors errors) {
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    MemberMst member = memberService.signUp(request);
    if(member == null){
      return response.fail("이미 존재하는 계정 정보입니다.", HttpStatus.BAD_REQUEST);
    }
    return memberService.authorityInsert(request);
  }

  @PostMapping("/reissue")
  public ResponseEntity<?> refresh(@RequestBody TokenDto token, Errors errors) {
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return memberService.refreshAccessToken(token);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(@RequestBody LogoutRequest request, Errors errors){
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return memberService.logout(request);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> delete(@RequestBody DeleteAccountRequest request, Errors errors){
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return memberService.delete(request);
  }

  @PostMapping("/test")
  public ResponseEntity< ? > test(@Valid @RequestBody TestRequest request, Errors errors){
    if(errors.hasErrors()){
      return response.invalidFields(Helper.refineErrors(errors));
    }
    return memberService.test(request);
  }
}
