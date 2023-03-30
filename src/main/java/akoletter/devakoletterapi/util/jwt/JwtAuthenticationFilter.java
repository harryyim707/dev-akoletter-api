package akoletter.devakoletterapi.util.jwt;

import akoletter.devakoletterapi.jpa.token.entity.Token;
import akoletter.devakoletterapi.jpa.token.repo.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = jwtProvider.resolveToken(request);
    if (token != null && jwtProvider.validateToken(token)) {
      // logout 여부 확인 -> isLogout이 false여야 로그인된 상태
      if(!jwtProvider.isLogout(token)){
        // check access token
        token = token.split(" ")[1].trim();
        Authentication auth = jwtProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    filterChain.doFilter(request, response);
  }

}
