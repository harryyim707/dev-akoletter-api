package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.common.member.domain.response.SignUpResponse;

public interface MemberService {

  LoginResponse login(LoginRequest request) throws Exception;
  SignUpResponse signUp(SignUpRequest request) throws Exception;
  LoginResponse getMember(String account) throws Exception;

}
