package com.ricardosilva.auth.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "auth_sessions")
public class AuthSessionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    private boolean active;

    public AuthSessionJpaEntity(boolean active, LocalDateTime expiresAt, LocalDateTime createdAt, String refreshToken, String accessToken, UUID userId, UUID id) {
        this.active = active;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.userId = userId;
        this.id = id;
    }

    public AuthSessionJpaEntity() {
    }
}