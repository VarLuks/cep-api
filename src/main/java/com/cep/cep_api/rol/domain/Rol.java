package com.cep.cep_api.rol.domain;
/*Siempre las clases al inicio en mayus*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "rol", schema = "auth")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @CreationTimestamp
    private Date created_at;
    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;
    @Column(nullable = false)
    private boolean is_active = true;
}
