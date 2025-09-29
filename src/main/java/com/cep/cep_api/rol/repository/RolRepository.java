package com.cep.cep_api.rol.repository;

import com.cep.cep_api.rol.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*el extends Jpa hace llamado a la db*/
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    //Optional<Rol> findById(Long id); ya está disponible automáticamente, no se necesita sobreescribir
}
