package com.mediLinkAI.services.Patient;

import com.mediLinkAI.dto.PatientProfileDTO;
import com.mediLinkAI.entity.Master.Location;
import com.mediLinkAI.entity.Patient.PatientProfile;
import com.mediLinkAI.entity.User.User;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Patient.PatientProfileRepository;
import com.mediLinkAI.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service(value = "patientProfileService")
public class PatientProfileServiceImpl implements PatientProfileService {

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    @Autowired
    private UserRepository userRepository;

    // Simple Base64 encryption/decryption for full_name_encrypted required by DB
    // schema.
    // Replace with a stronger standard encryption algorithm like AES if needed.
    private String encrypt(String raw) {
        if (raw == null)
            return null;
        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    private String decrypt(String encrypted) {
        if (encrypted == null)
            return null;
        return new String(Base64.getDecoder().decode(encrypted), StandardCharsets.UTF_8);
    }

    @Override
    public PatientProfileDTO createProfile(PatientProfileDTO dto) throws MediLinkAI {
        if (patientProfileRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new MediLinkAI("PROFILE_ALREADY_EXISTS");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));

        PatientProfile profile = new PatientProfile();
        profile.setUser(user);
        profile.setFullNameEncrypted(encrypt(dto.getFullName()));
        profile.setProfilePhotoUrl(dto.getProfilePhotoUrl());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());
        profile.setBloodGroup(dto.getBloodGroup());
        profile.setHeightCm(dto.getHeightCm());
        profile.setWeightKg(dto.getWeightKg());
        profile.setAbhaId(dto.getAbhaId());
        profile.setEmergencyCardEnabled(dto.getEmergencyCardEnabled() != null ? dto.getEmergencyCardEnabled() : false);
        profile.setPrivacyLevel(dto.getPrivacyLevel());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            profile.setLocation(loc);
        }

        profile = patientProfileRepository.save(profile);
        return mapToDTO(profile);
    }

    @Override
    public PatientProfileDTO getProfileByUserId(UUID userId) throws MediLinkAI {
        PatientProfile profile = patientProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        return mapToDTO(profile);
    }

    @Override
    public PatientProfileDTO updateProfile(UUID userId, PatientProfileDTO dto) throws MediLinkAI {
        PatientProfile profile = patientProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));

        if (dto.getFullName() != null) {
            profile.setFullNameEncrypted(encrypt(dto.getFullName()));
        }
        if (dto.getProfilePhotoUrl() != null)
            profile.setProfilePhotoUrl(dto.getProfilePhotoUrl());
        if (dto.getDateOfBirth() != null)
            profile.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getGender() != null)
            profile.setGender(dto.getGender());
        if (dto.getBloodGroup() != null)
            profile.setBloodGroup(dto.getBloodGroup());
        if (dto.getHeightCm() != null)
            profile.setHeightCm(dto.getHeightCm());
        if (dto.getWeightKg() != null)
            profile.setWeightKg(dto.getWeightKg());
        if (dto.getAbhaId() != null)
            profile.setAbhaId(dto.getAbhaId());
        if (dto.getEmergencyCardEnabled() != null)
            profile.setEmergencyCardEnabled(dto.getEmergencyCardEnabled());
        if (dto.getPrivacyLevel() != null)
            profile.setPrivacyLevel(dto.getPrivacyLevel());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            profile.setLocation(loc);
        }

        profile = patientProfileRepository.save(profile);
        return mapToDTO(profile);
    }

    @Override
    public void deleteProfile(UUID userId) throws MediLinkAI {
        PatientProfile profile = patientProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        profile.setIsDeleted(true);
        patientProfileRepository.save(profile);
    }

    private PatientProfileDTO mapToDTO(PatientProfile profile) {
        PatientProfileDTO dto = new PatientProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser() != null ? profile.getUser().getId() : null);
        dto.setFullName(decrypt(profile.getFullNameEncrypted()));
        dto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        dto.setLocationId(profile.getLocation() != null ? profile.getLocation().getId() : null);
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setGender(profile.getGender());
        dto.setBloodGroup(profile.getBloodGroup());
        dto.setHeightCm(profile.getHeightCm());
        dto.setWeightKg(profile.getWeightKg());
        dto.setAbhaId(profile.getAbhaId());
        dto.setEmergencyCardEnabled(profile.getEmergencyCardEnabled());
        dto.setPrivacyLevel(profile.getPrivacyLevel());
        return dto;
    }
}
