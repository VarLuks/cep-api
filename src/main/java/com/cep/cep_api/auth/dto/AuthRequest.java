package com.cep.cep_api.auth.dto;

public record AuthRequest (
        String email,
        String password
){
}
