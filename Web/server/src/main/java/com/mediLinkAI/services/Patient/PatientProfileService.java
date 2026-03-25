package com.mediLinkAI.services.Patient;

import com.mediLinkAI.dto.PatientProfileDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.UUID;

public interface PatientProfileService {
    PatientProfileDTO createProfile(PatientProfileDTO profileDTO) throws MediLinkAI;

    PatientProfileDTO getProfileByUserId(UUID userId) throws MediLinkAI;

    PatientProfileDTO updateProfile(UUID userId, PatientProfileDTO profileDTO) throws MediLinkAI;

    void deleteProfile(UUID userId) throws MediLinkAI;
}
