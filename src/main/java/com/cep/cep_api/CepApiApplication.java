package com.cep.cep_api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@SpringBootApplication
@RestController
public class CepApiApplication {
    private final ObjectMapper objectMapper;

    public CepApiApplication(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(CepApiApplication.class, args);
    }


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, String>> getUser() {
        System.out.println("getUser");
        System.out.println("1");
        System.out.println("2");
        return ResponseEntity.ok().body(Map.of("message", "Esto es un JSON de usuario"));
    }

    @GetMapping("/prueba")
    public String prueba() throws JsonProcessingException {
        Usuario lucas = new Usuario(1, "lucas", 2025);
        Usuario maria = new Usuario(2, "maria", 2025);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(lucas);
        usuarios.add(maria);
        return objectMapper.writeValueAsString(usuarios);
    }

    @PostMapping("/enviar")
    public ResponseEntity<Map<String, String>> post(@RequestBody java.lang.String value) throws JsonProcessingException {
        Usuario lucas = new Usuario(1, "lucas", 2025);
        Usuario maria = new Usuario(2, "maria", 2025);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(lucas);
        usuarios.add(maria);
        JsonNode nodo = objectMapper.readTree(value);
        return ResponseEntity.ok().body(Map.of("message", usuarios.get(nodo.get("id").asInt()-1).nombre));
    }

    @PutMapping("/update")
    public String put(@RequestBody String value) throws JsonProcessingException {
        JsonNode nodo = objectMapper.readTree(value);
        Usuario lucas = new Usuario(1, "lucas", 2025);
        Usuario maria = new Usuario(2, "maria", 2025);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(lucas);
        usuarios.add(maria);
        for (Usuario usuario : usuarios) {
            if (usuario.nombre.equals(nodo.get("nombre").asText()) && usuario.anho == nodo.get("anho").asInt()) {
                usuario.nombre = "Carlos";
                usuario.anho = 2023;
                break;
            }
        }
        return objectMapper.writeValueAsString(lucas);
    }

}
