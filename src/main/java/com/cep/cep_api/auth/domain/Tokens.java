package com.cep.cep_api.auth.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Builder
@Setter
@Getter
@Entity
@Table(name = "tokens", schema = "auth")
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {
    public enum token_type_enum{
        Bearer;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    boolean expired;
    boolean revoked;
    @Column(nullable = false)
    String token;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "token_type", columnDefinition = "auth.token_type_enum")
    private token_type_enum token_type;
    @Column(nullable = false)
    @CreationTimestamp
    private Date created_at;
    @Column(nullable = false)
    @UpdateTimestamp
    private Date modified_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
