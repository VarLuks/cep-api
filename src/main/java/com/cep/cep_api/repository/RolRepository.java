package com.cep.cep_api.repository;

import com.cep.cep_api.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/*el extends Jpa hace llamado a la db*/
public interface RolRepository extends JpaRepository<Rol, Long> {
    @Override
    Optional<Rol> findById(Long id);
}
