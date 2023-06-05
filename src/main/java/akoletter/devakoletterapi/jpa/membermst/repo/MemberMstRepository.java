package akoletter.devakoletterapi.jpa.membermst.repo;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMstPk;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMstRepository extends JpaRepository<MemberMst, MemberMstPk> {

  Optional<MemberMst> findByUsrId(String usrId);

  Optional<MemberMst> findByUsrEmail(String usrEmail);

  Optional<MemberMst> findByUsrTelNo(String usrTelNo);

  Optional<MemberMst> findByUnqUsrId(Long unqUsrId);

  Optional<MemberMst> findTopByOrderByUnqUsrIdDesc();
}
