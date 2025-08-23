package com.cep.cep_api.controller;

import com.cep.cep_api.dto.RolResponse;
import com.cep.cep_api.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rol")
public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/{id}")
    public RolResponse get(@PathVariable Long id) {
        return rolService.getById(id);
    }
}
