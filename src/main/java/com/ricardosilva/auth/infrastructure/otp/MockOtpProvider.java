package com.ricardosilva.auth.infrastructure.otp;

import com.ricardosilva.auth.domain.port.OtpProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class MockOtpProvider implements OtpProvider {

    private static final Logger logger = LoggerFactory.getLogger(MockOtpProvider.class);

    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public void sendOtp(String username) {

        String otp = generateOtp();

        otpStorage.put(username, otp);

        logger.info("OTP generated for user={} otp={}", username, otp);
    }

    @Override
    public boolean validateOtp(String username, String otp) {

        String storedOtp = otpStorage.get(username);

        return storedOtp != null && storedOtp.equals(otp);
    }

    private String generateOtp() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}