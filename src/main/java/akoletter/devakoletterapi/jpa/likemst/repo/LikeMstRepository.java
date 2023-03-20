package akoletter.devakoletterapi.jpa.likemst.repo;

import akoletter.devakoletterapi.jpa.likemst.entity.LikeMst;
import akoletter.devakoletterapi.jpa.likemst.entity.LikeMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeMstRepository extends JpaRepository<LikeMst, LikeMstPk> {
}
