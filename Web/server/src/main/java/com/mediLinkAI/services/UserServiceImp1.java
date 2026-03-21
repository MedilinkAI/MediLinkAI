package com.mediLinkAI.services;

import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mediLinkAI.dto.LoginDTO;
import com.mediLinkAI.dto.ResponseDTO;
import com.mediLinkAI.dto.UserDTO;
import com.mediLinkAI.entity.OTP;
import com.mediLinkAI.entity.User;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.OTPRepository;
import com.mediLinkAI.repository.UserRepository;
import com.mediLinkAI.utility.Data;
import com.mediLinkAI.utility.Utilities;

@Service(value = "userService")
public class UserServiceImp1 implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private OTPRepository otpRepository;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws MediLinkAI {
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
        if (optional.isPresent()) {
            throw new MediLinkAI("USER_FOUND");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        user = userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws MediLinkAI {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new MediLinkAI("INVALID_PASSWORD");
        return user.toDTO();
    }

    @Override
    public void sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("OTP Verification - NutriTrack");
        String otp = Utilities.generateOtp();

        // Check if OTP record exists for this email
        Optional<OTP> existingOtp = otpRepository.findByEmail(email);
        OTP otpSave;

        if (existingOtp.isPresent()) {
            // Update existing OTP record
            otpSave = existingOtp.get();
            otpSave.setOtp(otp);
            otpSave.setCreatedAt(LocalDateTime.now());
        } else {
            // Create new OTP record
            otpSave = new OTP(null, email, otp, LocalDateTime.now());
        }

        otpRepository.save(otpSave);
        message.setText(Data.getOtpEmailHtml(otp, user.getName()), true);
        mailSender.send(mm);
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws MediLinkAI {
        // Verify user exists
        userRepository.findByEmail(email).orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));

        // Find OTP record
        Optional<OTP> otpRecord = otpRepository.findByEmailAndOtp(email, otp);
        if (otpRecord.isEmpty()) {
            throw new MediLinkAI("INVALID_OTP");
        }

        OTP otpEntity = otpRecord.get();

        // Check if OTP is expired (10 minutes)
        LocalDateTime expiryTime = otpEntity.getCreatedAt().plusMinutes(10);
        if (LocalDateTime.now().isAfter(expiryTime)) {
            otpRepository.delete(otpEntity);
            throw new MediLinkAI("OTP_EXPIRED");
        }

        // Delete OTP after successful verification (one-time use)
        otpRepository.delete(otpEntity);

        return true;
    }

    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws MediLinkAI {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        return new ResponseDTO("Password changed successfully");
    }

    @Scheduled(fixedRate = 10000)
    public void removeExpiredOTP() {
        List<OTP> expiredOTPs = otpRepository.findAllByCreatedAtBefore(LocalDateTime.now().minusMinutes(10));
        if (expiredOTPs.size() > 0)
            otpRepository.deleteAll(expiredOTPs);
        System.out.println("Expired OTPs removed: " + expiredOTPs.size());
    }
}
