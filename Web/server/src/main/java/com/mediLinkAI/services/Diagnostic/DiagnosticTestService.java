package com.mediLinkAI.services.Diagnostic;

import com.mediLinkAI.dto.DiagnosticTestDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.List;
import java.util.UUID;

public interface DiagnosticTestService {
    DiagnosticTestDTO addTest(DiagnosticTestDTO dto) throws MediLinkAI;

    List<DiagnosticTestDTO> getTestsByDiagnosticId(UUID diagnosticProfileId) throws MediLinkAI;

    DiagnosticTestDTO updateTest(UUID id, DiagnosticTestDTO dto) throws MediLinkAI;

    void removeTest(UUID id) throws MediLinkAI;
}
