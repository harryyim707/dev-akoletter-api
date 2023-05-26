package akoletter.devakoletterapi.jpa.postmst.repo;

import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostMstRepository extends JpaRepository<PostMst, PostMstPk> {


    //PostMst findTop10ByOrderByfrstRgstDt();
    List<PostMst> findTop12By();

    List<PostMst> findTop12ByCategory(String category);

    Optional<PostMst> findByPostId(Long postId);

    Optional<PostMst> findByPostTitleAndUnqUsrId(String postTitle, Long unqUsrId);

    Boolean existsByPostTitle(String postTitle);


  List<PostMst> findTop12ByCategoryAndPostIdNotIn(String category, List<Long> idList);

    List<PostMst> findTop12ByPostIdNotIn(List<Long> idList);
}
