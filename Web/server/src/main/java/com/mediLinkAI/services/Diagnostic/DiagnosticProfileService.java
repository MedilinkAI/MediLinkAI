package com.mediLinkAI.services.Diagnostic;

import com.mediLinkAI.dto.DiagnosticProfileDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.UUID;

public interface DiagnosticProfileService {
    DiagnosticProfileDTO createProfile(DiagnosticProfileDTO dto) throws MediLinkAI;

    DiagnosticProfileDTO getProfileByUserId(UUID userId) throws MediLinkAI;

    DiagnosticProfileDTO getProfileById(UUID id) throws MediLinkAI;

    DiagnosticProfileDTO updateProfile(UUID userId, DiagnosticProfileDTO dto) throws MediLinkAI;

    void deleteProfile(UUID userId) throws MediLinkAI;
}
