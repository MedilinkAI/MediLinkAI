package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.SpecializationDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.List;
import java.util.UUID;

public interface SpecializationService {
    SpecializationDTO createSpecialization(SpecializationDTO dto) throws MediLinkAI;

    SpecializationDTO getSpecializationById(UUID id) throws MediLinkAI;

    List<SpecializationDTO> getAllSpecializations();

    SpecializationDTO updateSpecialization(UUID id, SpecializationDTO dto) throws MediLinkAI;

    void deleteSpecialization(UUID id) throws MediLinkAI;
}
