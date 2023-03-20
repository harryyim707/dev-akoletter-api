package akoletter.devakoletterapi.jpa.codedtl.repo;

import akoletter.devakoletterapi.jpa.codedtl.entity.CodeDtl;
import akoletter.devakoletterapi.jpa.codedtl.entity.CodeDtlPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDtlRepository extends JpaRepository<CodeDtl, CodeDtlPk> {
}
