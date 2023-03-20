package akoletter.devakoletterapi.jpa.codemst.repo;

import akoletter.devakoletterapi.jpa.codemst.entity.CodeMst;
import akoletter.devakoletterapi.jpa.codemst.entity.CodeMstPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeMstRepository extends JpaRepository<CodeMst, CodeMstPk> {
}
