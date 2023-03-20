package akoletter.devakoletterapi.jpa.membersub.repo;

import akoletter.devakoletterapi.jpa.membersub.entity.MemberSub;
import akoletter.devakoletterapi.jpa.membersub.entity.MemberSubPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSubRepository extends JpaRepository<MemberSub, MemberSubPk> {
}
