package com.mediLinkAI.services.Diagnostic;

import com.mediLinkAI.dto.MedicalTestDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.List;
import java.util.UUID;

public interface MedicalTestService {
    MedicalTestDTO createTest(MedicalTestDTO dto) throws MediLinkAI;

    MedicalTestDTO getTestById(UUID id) throws MediLinkAI;

    List<MedicalTestDTO> getAllTests();

    List<MedicalTestDTO> searchTestsByName(String name);

    List<MedicalTestDTO> getTestsByCategory(String category);

    MedicalTestDTO updateTest(UUID id, MedicalTestDTO dto) throws MediLinkAI;

    void deleteTest(UUID id) throws MediLinkAI;
}
