package com.cep.cep_api.auth.dto;

public record   RegisterRequest(
        String document_number,
        String email,
        String name,
        String lastname,
        String phone_number,
        String password
) {

}
