package akoletter.devakoletterapi.jpa.postmst.repo;

import akoletter.devakoletterapi.jpa.membermst.entity.MemberMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMstPk;
import akoletter.devakoletterapi.post.PostListLoad.domain.request.PostListLoadRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostMstRepository extends JpaRepository<PostMst, PostMstPk> {


    //PostMst findTop10ByOrderByfrstRgstDt();
    List<PostMst> findTop10By();

    Optional<PostMst> findByPostId(Long postId);


}
