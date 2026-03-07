package com.ricardosilva.auth.infrastructure.persistence.adapter;

import com.ricardosilva.auth.application.port.out.AuthSessionRepositoryPort;
import com.ricardosilva.auth.domain.model.AuthSession;
import com.ricardosilva.auth.infrastructure.persistence.entity.AuthSessionJpaEntity;
import com.ricardosilva.auth.infrastructure.persistence.repository.SpringDataAuthSessionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthSessionRepositoryAdapter
        implements AuthSessionRepositoryPort {

    private final SpringDataAuthSessionRepository repository;

    public AuthSessionRepositoryAdapter(
            SpringDataAuthSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthSession save(AuthSession session) {

        AuthSessionJpaEntity entity = toEntity(session);

        AuthSessionJpaEntity saved = repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<AuthSession> findByAccessToken(String token) {
        return Optional.empty();
    }
    private AuthSessionJpaEntity toEntity(AuthSession session) {
        AuthSessionJpaEntity entity = new AuthSessionJpaEntity();
        entity.setId(session.getId());
        entity.setUsername(session.getUsername());
        entity.setStatus(session.getStatus());     // idealmente @Enumerated(EnumType.STRING)
        entity.setAttempts(session.getAttempts());
        entity.setCreatedAt(session.getCreatedAt());
        return entity;
    }

    private AuthSession toDomain(AuthSessionJpaEntity entity) {
        return new AuthSession(
                entity.getId(),
                entity.getUsername(),
                entity.getStatus(),
                entity.getAttempts(),
                entity.getCreatedAt()
        );
    }
}