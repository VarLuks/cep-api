package com.cep.cep_api.service;

import com.cep.cep_api.domain.Rol;
import com.cep.cep_api.dto.*;
import com.cep.cep_api.repository.RolRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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
    } //ACA ME QUEDE
    @Transactional
    public RolListResponse getAll() throws JsonProcessingException {
        List<Rol> roles = rolRepository.findAll();
        return RolListMapper.toRolListResponse(roles);
    }
    @Transactional
    public RolResponse setById(Long id, String description){
        Optional<Rol> rol = rolRepository.findById(id);
        rol.get().setDescription(description);
        return RolMapper.toRolResponse(rol.get());
    }
    @Transactional
    public RolResponse addNewRol(Rol newRol) {
        Optional<Rol> rol = Optional.of(rolRepository.save(newRol));
        return RolMapper.toRolResponse(rol.get());
    }
    @Transactional
    public RolResponse DeleteRol (Long idDelete) {
        Optional<Rol> rol = rolRepository.findById(idDelete);
        rol.get().set_active(false);
        return RolMapper.toRolResponse(rol.get());
    }
}
