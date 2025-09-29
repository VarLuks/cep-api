package com.cep.cep_api.auth.repository;
import com.cep.cep_api.auth.domain.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionsRepository extends JpaRepository<Sessions, Long> {

}

