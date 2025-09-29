package com.cep.cep_api.auth.repository;

import com.cep.cep_api.auth.domain.Tokens;
import com.cep.cep_api.auth.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
