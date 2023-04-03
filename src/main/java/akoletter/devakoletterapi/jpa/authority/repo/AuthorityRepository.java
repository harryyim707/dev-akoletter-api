package akoletter.devakoletterapi.jpa.authority.repo;

import akoletter.devakoletterapi.jpa.authority.entity.Authority;
import akoletter.devakoletterapi.jpa.authority.entity.AuthorityPk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityPk> {
//  List<Authority> findAllByMember(Long unqUsrId);
}
