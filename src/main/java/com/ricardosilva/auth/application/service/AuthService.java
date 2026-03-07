package com.ricardosilva.auth.application.service;

import com.ricardosilva.auth.domain.exception.AuthException;
import com.ricardosilva.auth.domain.model.AuthSession;
import com.ricardosilva.auth.domain.model.AuthStatus;
import com.ricardosilva.auth.domain.port.AuthRepository;
import com.ricardosilva.auth.domain.port.OtpProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthRepository authRepository;
    private final OtpProvider otpProvider;

    public AuthService(AuthRepository authRepository, OtpProvider otpProvider) {
        this.authRepository = authRepository;
        this.otpProvider = otpProvider;
    }

    public AuthSession startAuth(String username) {

        logger.info("Starting authentication flow for user={}", username);

        AuthSession session = new AuthSession(UUID.randomUUID(), username);

        otpProvider.sendOtp(username);
        session.markOtpSent();

        AuthSession saved = authRepository.save(session);

        logger.info("OTP sent successfully for sessionId={}", saved.getId());

        return saved;
    }

    public AuthSession validateOtp(UUID sessionId, String otp) {

        logger.info("Validating OTP for sessionId={}", sessionId);

        AuthSession session = authRepository.findById(sessionId)
                .orElseThrow(() -> new AuthException("Session not found"));

        if (session.getStatus() == AuthStatus.LOCKED) {
            throw new AuthException("Session is locked");
        }

        if (session.getStatus() != AuthStatus.OTP_SENT) {
            throw new AuthException("Invalid authentication state");
        }

        boolean valid = otpProvider.validateOtp(session.getUsername(), otp);

        if (!valid) {
            session.incrementAttempts();
            authRepository.save(session);

            logger.warn("Invalid OTP attempt for sessionId={}", sessionId);

            if (session.getStatus() == AuthStatus.LOCKED) {
                logger.warn("Session locked due to max attempts sessionId={}", sessionId);
            }

            throw new AuthException("Invalid OTP");
        }

        session.markAuthenticated();
        authRepository.save(session);

        logger.info("User authenticated successfully sessionId={}", sessionId);

        return session;
    }
}