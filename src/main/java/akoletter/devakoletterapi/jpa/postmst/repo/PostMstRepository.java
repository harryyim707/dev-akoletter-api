package akoletter.devakoletterapi.jpa.postmst.repo;

import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMstPk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostMstRepository extends JpaRepository<PostMst, PostMstPk> {

  Slice<PostMst> findByUseYn(Pageable pageable, String useYn);

  Optional<PostMst> findByPostIdAndUseYn(Long postId, String useYn);

  Optional<PostMst> findByPostTitleAndUnqUsrIdAndUseYn(String postTitle, Long unqUsrId, String useYn);

  Boolean existsByPostTitleAndUseYn(String postTitle, String useYn);

  Slice<PostMst> findByCategoryAndUseYn(String category, Pageable pageable, String useYn);

  Optional<PostMst> findTopByUseYnOrderByPostIdDesc(String useYn);
}
