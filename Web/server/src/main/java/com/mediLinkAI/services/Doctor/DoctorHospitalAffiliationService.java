package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.DoctorHospitalAffiliationDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.List;
import java.util.UUID;

public interface DoctorHospitalAffiliationService {
    DoctorHospitalAffiliationDTO addAffiliation(DoctorHospitalAffiliationDTO dto) throws MediLinkAI;

    List<DoctorHospitalAffiliationDTO> getAffiliationsByDoctorId(UUID doctorProfileId) throws MediLinkAI;

    DoctorHospitalAffiliationDTO updateAffiliation(UUID id, DoctorHospitalAffiliationDTO dto) throws MediLinkAI;

    void removeAffiliation(UUID id) throws MediLinkAI;
}
