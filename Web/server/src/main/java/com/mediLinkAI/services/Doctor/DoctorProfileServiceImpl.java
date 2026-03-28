package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.DoctorProfileDTO;
import com.mediLinkAI.entity.Doctor.DoctorProfile;
import com.mediLinkAI.entity.Master.Location;
import com.mediLinkAI.entity.User.User;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Doctor.DoctorProfileRepository;
import com.mediLinkAI.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Service(value = "doctorProfileService")
@Transactional
public class DoctorProfileServiceImpl implements DoctorProfileService {

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    @Autowired
    private UserRepository userRepository;

    private String encrypt(String raw) {
        if (raw == null) return null;
        return Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    private String decrypt(String encrypted) {
        if (encrypted == null) return null;
        return new String(Base64.getDecoder().decode(encrypted), StandardCharsets.UTF_8);
    }

    @Override
    public DoctorProfileDTO createProfile(DoctorProfileDTO dto) throws MediLinkAI {
        if (doctorProfileRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new MediLinkAI("PROFILE_ALREADY_EXISTS");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new MediLinkAI("USER_NOT_FOUND"));

        DoctorProfile profile = new DoctorProfile();
        profile.setUser(user);
        profile.setFullNameEncrypted(encrypt(dto.getFullName()));
        profile.setProfilePhotoUrl(dto.getProfilePhotoUrl());
        profile.setDateOfBirth(dto.getDateOfBirth());
        profile.setGender(dto.getGender());
        profile.setHighestQualification(dto.getHighestQualification());
        profile.setExperienceYears(dto.getExperienceYears());
        profile.setLicenseNumber(dto.getLicenseNumber());
        profile.setLicenseAuthority(dto.getLicenseAuthority());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            profile.setLocation(loc);
        }

        profile = doctorProfileRepository.save(profile);
        return mapToDTO(profile);
    }

    @Override
    public DoctorProfileDTO getProfileByUserId(UUID userId) throws MediLinkAI {
        DoctorProfile profile = doctorProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        return mapToDTO(profile);
    }

    @Override
    public DoctorProfileDTO updateProfile(UUID userId, DoctorProfileDTO dto) throws MediLinkAI {
        DoctorProfile profile = doctorProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));

        if (dto.getFullName() != null) {
            profile.setFullNameEncrypted(encrypt(dto.getFullName()));
        }
        if (dto.getProfilePhotoUrl() != null) profile.setProfilePhotoUrl(dto.getProfilePhotoUrl());
        if (dto.getDateOfBirth() != null) profile.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getGender() != null) profile.setGender(dto.getGender());
        if (dto.getHighestQualification() != null) profile.setHighestQualification(dto.getHighestQualification());
        if (dto.getExperienceYears() != null) profile.setExperienceYears(dto.getExperienceYears());
        if (dto.getLicenseNumber() != null) profile.setLicenseNumber(dto.getLicenseNumber());
        if (dto.getLicenseAuthority() != null) profile.setLicenseAuthority(dto.getLicenseAuthority());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            profile.setLocation(loc);
        }

        profile = doctorProfileRepository.save(profile);
        return mapToDTO(profile);
    }

    @Override
    public void deleteProfile(UUID userId) throws MediLinkAI {
        DoctorProfile profile = doctorProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new MediLinkAI("PROFILE_NOT_FOUND"));
        doctorProfileRepository.delete(profile);
    }

    private DoctorProfileDTO mapToDTO(DoctorProfile profile) {
        DoctorProfileDTO dto = new DoctorProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser() != null ? profile.getUser().getId() : null);
        dto.setFullName(decrypt(profile.getFullNameEncrypted()));
        dto.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        dto.setLocationId(profile.getLocation() != null ? profile.getLocation().getId() : null);
        dto.setDateOfBirth(profile.getDateOfBirth());
        dto.setGender(profile.getGender());
        dto.setHighestQualification(profile.getHighestQualification());
        dto.setExperienceYears(profile.getExperienceYears());
        dto.setLicenseNumber(profile.getLicenseNumber());
        dto.setLicenseAuthority(profile.getLicenseAuthority());
        return dto;
    }
}
