package com.ricardosilva.auth.domain.model;

public enum AuthStatus {
    PENDING,
    OTP_SENT,
    AUTHENTICATED,
    LOCKED,
    EXPIRED
}