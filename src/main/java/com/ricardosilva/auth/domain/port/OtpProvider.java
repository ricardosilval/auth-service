package com.ricardosilva.auth.domain.port;

public interface OtpProvider {

    void sendOtp(String username);

    boolean validateOtp(String username, String otp);
}