package com.cep.cep_api.auth.controller;

import com.cep.cep_api.auth.dto.AuthRequest;
import com.cep.cep_api.auth.dto.RegisterRequest;
import com.cep.cep_api.auth.dto.TokenResponse;
import com.cep.cep_api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request) throws Exception {
        final TokenResponse token = service.register(request);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest request){
        final TokenResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return service.refreshToken(authentication);
    }

}
