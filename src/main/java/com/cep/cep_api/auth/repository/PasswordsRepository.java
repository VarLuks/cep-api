package com.cep.cep_api.auth.repository;

import com.cep.cep_api.auth.domain.Passwords;
import com.cep.cep_api.auth.domain.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordsRepository extends JpaRepository<Passwords, Long> {
    Optional<Passwords> findByUserId(Long user_id);
}

