package akoletter.devakoletterapi.jpa.token.repo;

import akoletter.devakoletterapi.jpa.token.entity.Token;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
}
