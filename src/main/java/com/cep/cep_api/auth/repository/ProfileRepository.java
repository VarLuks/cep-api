package com.cep.cep_api.auth.repository;

import com.cep.cep_api.auth.domain.Profile;
import com.cep.cep_api.auth.domain.Tokens;
import com.cep.cep_api.auth.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long user_id);
    Optional<Profile> findByDocumentNumber(String document_number);

}
