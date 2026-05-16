package com.mediLinkAI.services.Master;

import com.mediLinkAI.dto.HospitalDTO;
import com.mediLinkAI.exception.MediLinkAI;

import java.util.List;
import java.util.UUID;

public interface HospitalService {
    HospitalDTO createHospital(HospitalDTO dto) throws MediLinkAI;

    HospitalDTO getHospitalById(UUID id) throws MediLinkAI;

    List<HospitalDTO> getAllHospitals();

    List<HospitalDTO> searchHospitalsByName(String name);

    List<HospitalDTO> getHospitalsByLocationId(UUID locationId);

    HospitalDTO updateHospital(UUID id, HospitalDTO dto) throws MediLinkAI;

    void deleteHospital(UUID id) throws MediLinkAI;
}
