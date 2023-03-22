package akoletter.devakoletterapi.jpa.membermst.repo;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.membermst.entity.MemberMstPk;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMstRepository extends JpaRepository<MemberMst, MemberMstPk> {

  MemberMst findByUsrId(String usrId);
}
