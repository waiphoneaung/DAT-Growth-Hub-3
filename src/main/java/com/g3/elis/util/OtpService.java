package com.g3.elis.util;

import java.security.SecureRandom;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g3.elis.model.OtpRequest;
import com.g3.elis.repository.OtpRequestRepository;

@Service
public class OtpService {
    @Autowired
    private OtpRequestRepository otpRequestRepository;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    public String generateOtp(String email) {
        String otp = generateSecureOtp();
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setEmail(email);
        otpRequest.setOtp(otp);
        otpRequestRepository.save(otpRequest);
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        OtpRequest otpRequest = otpRequestRepository.findByEmailAndOtpAndIsUsedFalse(email, otp);
        if (otpRequest != null && otpRequest.getExpiresAt().after(new Timestamp(System.currentTimeMillis()))) {
            otpRequest.setUsed(true);
            otpRequestRepository.save(otpRequest);
            return true;
        }
        return false;
    }

    private String generateSecureOtp() {
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(secureRandom.nextInt(10));
        }
        return otp.toString();
    }
}
