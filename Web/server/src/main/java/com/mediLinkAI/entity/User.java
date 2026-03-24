package com.mediLinkAI.entity;

import com.mediLinkAI.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_hash", nullable = false)
    private String phoneHash;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(name = "mfa_enabled", nullable = false, columnDefinition = "boolean default false")
    private Boolean mfaEnabled = false;

    @Column(name = "mfa_secret")
    private String mfaSecret;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "failed_login_count", nullable = false, columnDefinition = "int default 0")
    private Integer failedLoginCount = 0;

    @Column(name = "password_changed_at")
    private LocalDateTime passwordChangedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    public com.mediLinkAI.dto.UserDTO toDTO() {
        com.mediLinkAI.dto.UserDTO dto = new com.mediLinkAI.dto.UserDTO();
        dto.setId(this.id);
        dto.setEmail(this.email);
        return dto;
    }
}