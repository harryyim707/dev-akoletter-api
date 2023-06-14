package akoletter.devakoletterapi.jpa.filemst.repo;

import akoletter.devakoletterapi.jpa.filemst.entity.FileMst;
import akoletter.devakoletterapi.jpa.filemst.entity.FileMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileMstRepository extends JpaRepository<FileMst, FileMstPk> {

  Optional<FileMst> findByfileIdAndUseYn(int fileId,String useYn);
  Optional<FileMst> findByfileIdAndUseYnOrderByFileIdDesc(int fileId, String useYn);

  Optional<FileMst> findTopByUseYnOrderByFileIdDesc(String useYn);
}
