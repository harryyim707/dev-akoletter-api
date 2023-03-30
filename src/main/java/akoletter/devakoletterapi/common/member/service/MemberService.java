package akoletter.devakoletterapi.common.member.service;

import akoletter.devakoletterapi.common.member.domain.request.LoginRequest;
import akoletter.devakoletterapi.common.member.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.member.domain.response.LoginResponse;
import akoletter.devakoletterapi.common.member.domain.response.SignUpResponse;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.token.entity.Token;
import akoletter.devakoletterapi.util.jwt.TokenDto;

public interface MemberService {

  LoginResponse login(LoginRequest request) throws Exception;
  SignUpResponse signUp(SignUpRequest request) throws Exception;
  LoginResponse getMember(String account) throws Exception;
  String createRefreshToken(MemberMst memberMst);

  Token validRefreshToken(MemberMst memberMst, String refreshToken) throws Exception;

  TokenDto refreshAccessToken(TokenDto token) throws Exception;

}
