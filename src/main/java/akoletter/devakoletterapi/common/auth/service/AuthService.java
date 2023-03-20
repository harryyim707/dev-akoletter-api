package akoletter.devakoletterapi.common.auth.service;

import akoletter.devakoletterapi.common.auth.domain.request.SignUpRequest;
import akoletter.devakoletterapi.common.auth.domain.response.SignUpResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
  SignUpResponse signUp(@Valid @RequestBody SignUpRequest request);

}
