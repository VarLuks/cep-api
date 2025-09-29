package com.cep.cep_api.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions", schema = "auth")
public class Sessions {
    public enum session_status_enum{
        active, inactive;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "session_status", columnDefinition = "auth.session_status_enum")
    private session_status_enum session_status;
    @CreationTimestamp
    private Date created_at;
    @UpdateTimestamp
    private Date modified_at;


}
