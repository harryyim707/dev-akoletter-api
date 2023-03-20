package akoletter.devakoletterapi.jpa.commentmst.repo;

import akoletter.devakoletterapi.jpa.commentmst.entity.CommentMst;
import akoletter.devakoletterapi.jpa.commentmst.entity.CommentMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentMstRepository extends JpaRepository<CommentMst, CommentMstPk> {
}
