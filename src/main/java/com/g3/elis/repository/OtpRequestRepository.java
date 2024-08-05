package com.g3.elis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g3.elis.model.OtpRequest;

public interface OtpRequestRepository extends JpaRepository<OtpRequest, Integer> {
    OtpRequest findByEmailAndOtpAndIsUsedFalse(String email, String otp);
}