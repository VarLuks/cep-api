package com.cep.cep_api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CepApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CepApiApplication.class, args);
    }
}
/*
Tiene que haber un getAll, que se representa solo con /rol
Tiene que haber un actualizar, (PUT) que también debe ser un /rol
Tiene que haber un agregar rol, (POST) que también debe ser /rol
Tiene que haber un delete y tiene que ser lógico, no debe borrar una fila
Todos deben tener su 400, 404, 200, 201, 202
*/