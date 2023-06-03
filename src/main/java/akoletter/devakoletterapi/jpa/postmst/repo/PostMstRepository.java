package akoletter.devakoletterapi.jpa.postmst.repo;

import akoletter.devakoletterapi.jpa.postmst.entity.PostMst;
import akoletter.devakoletterapi.jpa.postmst.entity.PostMstPk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostMstRepository extends JpaRepository<PostMst, PostMstPk> {

  Slice<PostMst> findBy(Pageable pageable);

  List<PostMst> findTop12ByCategory(String category);

  Optional<PostMst> findByPostId(Long postId);

  Optional<PostMst> findByPostTitleAndUnqUsrId(String postTitle, Long unqUsrId);

  Boolean existsByPostTitle(String postTitle);

  Slice<PostMst> findByCategory(String category, Pageable pageable);
}
