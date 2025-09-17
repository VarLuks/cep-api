package com.cep.cep_api.dto;

import com.cep.cep_api.domain.Rol;

public class RolMapper {
    public static RolResponse toRolResponse(Rol rol) {
        return new RolResponse(rol.getId(), rol.getDescription(), rol.getCreated_at(), rol.getModified_at());
    }
}