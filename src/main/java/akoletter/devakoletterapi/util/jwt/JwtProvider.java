package akoletter.devakoletterapi.util.jwt;

import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import akoletter.devakoletterapi.jpa.token.entity.Token;
import akoletter.devakoletterapi.jpa.token.repo.TokenRepository;
import akoletter.devakoletterapi.util.security.JpaUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtProvider {
  @Value("${jwt.secret}")
  private String salt;

  private Key secretKey;

  // access 토큰 만료시간 : 1Hour
  private final long exp = Duration.ofHours(1).toMillis();
//  private final long exp = 1000L * 30;

  private final JpaUserDetailsService userDetailsService;

  private final TokenRepository tokenRepository;
  private final MemberMstRepository memberMstRepository;
  @PostConstruct
  protected void init() {
    secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
  }

  // 토큰 생성
  public TokenDto createAccessToken(String account, List<Authority> roles) {
    Claims claims = Jwts.claims().setSubject(account);
    claims.put("roles", roles);
    Date now = new Date();
    Date accessTokenExpiration = new Date(now.getTime() + exp);
    String accessToken = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(accessTokenExpiration)
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
    Long accessTokenExpirationTime = accessTokenExpiration.getTime();
    return TokenDto.builder()
        .accessToken(accessToken)
        .accessTokenExpirationTime(accessTokenExpirationTime)
        .refreshToken(null)
        .refreshTokenExpirationTime(null)
        .build();
  }



  // 권한정보 획득
  // Spring Security 인증과정에서 권한확인을 위한 기능
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(this.getAccount(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  // 토큰에 담겨있는 유저 account 획득
  public String getAccount(String token) {
    // 만료된 토큰에 대해 parseClaimsJws를 수행하면 io.jsonwebtoken.ExpiredJwtException이 발생한다.
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    } catch (ExpiredJwtException e) {
      e.printStackTrace();
      return e.getClaims().getSubject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
  }

  // Authorization Header를 통해 인증을 한다.
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader("Authorization");
  }

  // 토큰 검증
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
      // 만료되었을 시 false
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  // 로그아웃 상태 확인
  // true = 로그아웃 된 상태, false = 로그인된 상태
  public boolean isLogout(String token){
    // token으로부터 유저 계정 정보 추출
    String usrId = this.getAccount(token);
    if(usrId == null){
      return true;
    }
    // 계정 정보 확인
    MemberMst memberMst = memberMstRepository.findByUsrId(usrId).orElse(null);
    if(memberMst == null || memberMst.getRefreshToken() == null){
      return true;
    }
    // Redis에서 해당 refresh token이 있는지 확인 = 로그인된 상태
    Token refreshToken = tokenRepository.findById(memberMst.getUnqUsrId()).orElse(null);
    // Redis에 refresh token이 없다면 로그아웃된 상태, refresh token의 만료시간이 0이하면 재로그인 필요
    return refreshToken == null || refreshToken.getExpiration() <= 0;
  }

  public Long getExpiration(String accessToken) {
    Date expiration = Jwts.parserBuilder().setSigningKey(secretKey)
        .build().parseClaimsJws(accessToken).getBody().getExpiration();
    Long now  = new Date().getTime();
    return (expiration.getTime() - now);
  }
}
