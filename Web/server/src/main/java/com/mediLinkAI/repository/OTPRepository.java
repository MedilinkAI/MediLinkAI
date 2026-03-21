package com.mediLinkAI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mediLinkAI.entity.OTP;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findByEmailAndOtp(String email, String otp);

    Optional<OTP> findByEmail(String email);

    List<OTP> findAllByCreatedAtBefore(LocalDateTime createdAt);
}
