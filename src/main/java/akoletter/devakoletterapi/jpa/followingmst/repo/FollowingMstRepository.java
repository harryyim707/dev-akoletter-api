package akoletter.devakoletterapi.jpa.followingmst.repo;

import akoletter.devakoletterapi.jpa.followingmst.entity.FollowingMst;
import akoletter.devakoletterapi.jpa.followingmst.entity.FollowingMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingMstRepository extends JpaRepository<FollowingMst, FollowingMstPk> {
}
