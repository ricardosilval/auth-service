package com.ricardosilva.auth.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuthSession {

    private UUID id;
    private String username;
    private AuthStatus status;
    private int attempts;
    private LocalDateTime createdAt;

    public AuthSession(UUID id, String username, AuthStatus status, int attempts, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.attempts = attempts;
        this.createdAt = createdAt;
    }

    public AuthSession(UUID id, String username) {
        this.id = id;
        this.username = username;
        this.status = AuthStatus.PENDING;
        this.attempts = 0;
        this.createdAt = LocalDateTime.now();
    }

    public void markOtpSent() {
        this.status = AuthStatus.OTP_SENT;
    }

    public void markAuthenticated() {
        this.status = AuthStatus.AUTHENTICATED;
    }

    public void incrementAttempts() {
        this.attempts++;
        if (this.attempts >= 3) {
            this.status = AuthStatus.LOCKED;
        }
    }

    // getters

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public AuthStatus getStatus() {
        return status;
    }

    public int getAttempts() {
        return attempts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}