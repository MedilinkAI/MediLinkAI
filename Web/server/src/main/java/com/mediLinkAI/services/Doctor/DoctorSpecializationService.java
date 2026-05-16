package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.DoctorSpecializationDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.List;
import java.util.UUID;

public interface DoctorSpecializationService {
    DoctorSpecializationDTO addSpecialization(DoctorSpecializationDTO dto) throws MediLinkAI;

    List<DoctorSpecializationDTO> getSpecializationsByDoctorId(UUID doctorProfileId) throws MediLinkAI;

    void removeSpecialization(UUID doctorProfileId, UUID specializationId) throws MediLinkAI;
}
