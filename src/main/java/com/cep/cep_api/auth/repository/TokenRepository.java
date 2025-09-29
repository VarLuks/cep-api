package com.cep.cep_api.auth.repository;

import com.cep.cep_api.auth.domain.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/*el extends Jpa hace llamado a la db*/
@Repository
public interface TokenRepository extends JpaRepository<Tokens, Integer> {

    @Query(value = """
      select t from Tokens t inner join Users u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)

    List<Tokens> findAllValidTokenByUser(Integer id);

    Optional<Tokens> findByToken(String token);
}
