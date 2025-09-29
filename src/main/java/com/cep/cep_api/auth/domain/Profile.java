package com.cep.cep_api.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile", schema = "auth")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "document_number")
    private String documentNumber;
    @Column (nullable = false)
    private String name;
    private String lastname;
    @Column (name = "phone_number")
    private String phoneNumber;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date modified_at;
    @OneToOne(mappedBy = "profile")
    private Users user;

}
