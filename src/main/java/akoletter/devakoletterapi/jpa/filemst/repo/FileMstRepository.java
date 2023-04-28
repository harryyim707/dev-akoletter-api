package akoletter.devakoletterapi.jpa.filemst.repo;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.filemst.entity.FileMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMstRepository extends JpaRepository<FileMst, Long> {
}
