package com.ricardosilva.auth.domain.port;

import com.ricardosilva.auth.domain.model.AuthSession;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository {

    AuthSession save(AuthSession session);

    Optional<AuthSession> findById(UUID id);
}