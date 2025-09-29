package com.cep.cep_api.rol.controller;

import com.cep.cep_api.rol.domain.Rol;
import com.cep.cep_api.rol.service.RolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rol")
public class RolController {
    ObjectMapper mapper = new ObjectMapper();
    private final RolService rolService;
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    //ResponseEntity se usa para que se manden justamente los valores por defecto que ya están cargados por spring
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable Long id) throws Exception{
        try {
            return ResponseEntity.ok(Map.of("data", rolService.getById(id)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(Map.of("Error", e.getMessage()));
        }
    }
    @GetMapping("")
    public ResponseEntity<Object> getAll() throws Exception{
        try {
            return ResponseEntity.ok(rolService.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(Map.of("Error", e.getMessage()));
        }
    }
    @PutMapping("")
    public ResponseEntity<Object> putString(@RequestBody String string) throws JsonProcessingException {
        JsonNode node = mapper.readTree(string);
        try {
            rolService.setById(node.get("id").asLong(), node.get("description").asText());
            return ResponseEntity.ok(Map.of("Cambio realizado:", node));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(Map.of("Error", e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> post(@RequestBody String value) throws JsonProcessingException {
        JsonNode node = mapper.readTree(value);
        Rol newRol = new Rol();
        newRol.setDescription(node.get("description").asText());
        try {
            rolService.addNewRol(newRol);
            return ResponseEntity.ok(Map.of("Nuevo rol agregado:", node));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(Map.of("Error", e.getMessage()));
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Object> delete(@RequestBody String id) throws JsonProcessingException {
        JsonNode node = mapper.readTree(id);
        Long idDelete = node.get("id").asLong();
        try {
            rolService.DeleteRol(idDelete);
            return ResponseEntity.ok(Map.of("Rol Eliminado:", rolService.getById(idDelete)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(Map.of("Error", e.getMessage()));
        }
    }
}
