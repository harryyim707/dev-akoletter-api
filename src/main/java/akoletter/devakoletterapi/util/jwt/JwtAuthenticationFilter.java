package akoletter.devakoletterapi.util.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtProvider jwtProvider;
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = jwtProvider.resolveToken(request);
    if (token != null && jwtProvider.validateToken(token)) {
      // logout 여부 확인 -> isLogout이 false여야 로그인된 상태
      if(!jwtProvider.isLogout(token) || ObjectUtils.isEmpty(redisTemplate.opsForValue().get(token))){
        // check access token
        Authentication auth = jwtProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
      else{
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "인증 정보가 없습니다.");
      }
    }

    filterChain.doFilter(request, response);
  }

}
