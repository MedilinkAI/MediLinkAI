package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.DoctorProfileDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.UUID;

public interface DoctorProfileService {
    DoctorProfileDTO createProfile(DoctorProfileDTO profileDTO) throws MediLinkAI;

    DoctorProfileDTO getProfileByUserId(UUID userId) throws MediLinkAI;

    DoctorProfileDTO updateProfile(UUID userId, DoctorProfileDTO profileDTO) throws MediLinkAI;

    void deleteProfile(UUID userId) throws MediLinkAI;
}
