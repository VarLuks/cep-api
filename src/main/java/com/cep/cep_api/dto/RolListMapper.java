package com.cep.cep_api.dto;

import com.cep.cep_api.domain.Rol;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;

public class RolListMapper {
    public static RolListResponse toRolListResponse(List<Rol> rolList) throws JsonProcessingException {
        List<RolResponse> roles = new ArrayList<>();
        for (Rol r :  rolList) {
            roles.add(new RolResponse(r.getId(), r.getDescription(), r.getCreated_at(), r.getModified_at()));
        }
        return new RolListResponse(roles);
    }
}