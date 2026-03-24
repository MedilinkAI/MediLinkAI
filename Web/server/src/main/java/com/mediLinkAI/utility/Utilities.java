package com.mediLinkAI.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Utilities {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String BASE32_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    public static String generateOtp() {
        StringBuilder otp = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    /**
     * Generates a Base32-encoded MFA secret for TOTP authentication.
     * Returns a 32-character Base32 string (160-bit secret).
     */
    public static String generateMfaSecret() {
        byte[] bytes = new byte[20]; // 160 bits
        secureRandom.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(BASE32_CHARS.charAt(Math.abs(b % 32)));
        }
        return sb.toString();
    }

    /**
     * Hashes a phone number using SHA-256 for deterministic encryption.
     * This allows lookups by hash while keeping the phone number secure.
     */
    public static String hashPhone(String phone) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(phone.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}
