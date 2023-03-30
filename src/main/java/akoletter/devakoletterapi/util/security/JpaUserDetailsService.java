package akoletter.devakoletterapi.util.security;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.repo.MemberMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
  private final MemberMstRepository memberMstRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    MemberMst member = memberMstRepository.findByUsrId(username).orElseThrow(() -> new UsernameNotFoundException("Invalid Authentication!"));
    return new CustomUserDetails(member);
  }
}
