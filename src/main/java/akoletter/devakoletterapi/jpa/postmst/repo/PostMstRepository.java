package akoletter.devakoletterapi.jpa.postmst.repo;

import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMstRepository extends JpaRepository<PostMst, PostMstPk> {

    //PostMst findTop10ByOrderByfrstRgstDt();
    PostMst findTop10By();
}
