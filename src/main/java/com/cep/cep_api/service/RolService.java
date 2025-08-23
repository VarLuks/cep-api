package com.cep.cep_api.service;

import com.cep.cep_api.domain.Rol;
import com.cep.cep_api.dto.RolMapper;
import com.cep.cep_api.dto.RolResponse;
import com.cep.cep_api.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    @Transactional
    public RolResponse getById(Long id){
        Optional<Rol> rol = rolRepository.findById(id);
        return RolMapper.toRolResponse(rol.get());
    }
}
