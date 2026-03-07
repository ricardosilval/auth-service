package com.ricardosilva.auth.application.port.out;

import com.ricardosilva.auth.domain.model.AuthSession;

import java.util.Optional;

public interface AuthSessionRepositoryPort {

    AuthSession save(AuthSession session);

    Optional<AuthSession> findByAccessToken(String token);

}