package com.cep.cep_api.domain;
/*Siempre las clases al inicio en mayus*/

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date created_at;
    @Column(nullable = false)
    private Date modified_at;

}
