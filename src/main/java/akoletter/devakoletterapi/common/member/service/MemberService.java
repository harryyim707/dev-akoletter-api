package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.DeleteAccountRequest;
import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.LogoutRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.request.TestRequest;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.util.jwt.TokenDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

  ResponseEntity<?> login(LoginRequest request);
  MemberMst signUp(SignUpRequest request);
  ResponseEntity<?>  logout(LogoutRequest request);
  ResponseEntity<?>  refreshAccessToken(TokenDto token);

  ResponseEntity<?> delete(DeleteAccountRequest request);

  ResponseEntity<?> authorityInsert(SignUpRequest request);
}
