package akoletter.devakoletterapi.util.security;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
  private final MemberMst member;

  public CustomUserDetails(MemberMst member){
    this.member = member;
  }
  public final MemberMst getMember(){
    return member;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return member.getRoles().stream().map(o -> new SimpleGrantedAuthority(o.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return member.getUsrPwd();
  }

  @Override
  public String getUsername() {
    return member.getUsrId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
