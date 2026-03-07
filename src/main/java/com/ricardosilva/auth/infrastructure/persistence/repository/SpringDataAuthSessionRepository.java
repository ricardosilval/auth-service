package com.ricardosilva.auth.infrastructure.persistence.repository;

import com.ricardosilva.auth.infrastructure.persistence.entity.AuthSessionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataAuthSessionRepository
        extends JpaRepository<AuthSessionJpaEntity, UUID> {

    Optional<AuthSessionJpaEntity> findByAccessToken(String accessToken);

}