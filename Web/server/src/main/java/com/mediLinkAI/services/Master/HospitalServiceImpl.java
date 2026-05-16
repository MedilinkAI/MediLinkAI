package com.mediLinkAI.services.Master;

import com.mediLinkAI.dto.HospitalDTO;
import com.mediLinkAI.entity.Master.Hospital;
import com.mediLinkAI.entity.Master.Location;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Master.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "hospitalService")
@Transactional
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public HospitalDTO createHospital(HospitalDTO dto) throws MediLinkAI {
        Hospital hospital = new Hospital();
        hospital.setName(dto.getName());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            hospital.setLocation(loc);
        }

        hospital = hospitalRepository.save(hospital);
        return mapToDTO(hospital);
    }

    @Override
    @Transactional(readOnly = true)
    public HospitalDTO getHospitalById(UUID id) throws MediLinkAI {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("HOSPITAL_NOT_FOUND"));
        return mapToDTO(hospital);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HospitalDTO> getAllHospitals() {
        return hospitalRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HospitalDTO> searchHospitalsByName(String name) {
        return hospitalRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HospitalDTO> getHospitalsByLocationId(UUID locationId) {
        return hospitalRepository.findByLocationId(locationId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HospitalDTO updateHospital(UUID id, HospitalDTO dto) throws MediLinkAI {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("HOSPITAL_NOT_FOUND"));

        if (dto.getName() != null) hospital.setName(dto.getName());

        if (dto.getLocationId() != null) {
            Location loc = new Location();
            loc.setId(dto.getLocationId());
            hospital.setLocation(loc);
        }

        hospital = hospitalRepository.save(hospital);
        return mapToDTO(hospital);
    }

    @Override
    public void deleteHospital(UUID id) throws MediLinkAI {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("HOSPITAL_NOT_FOUND"));
        hospitalRepository.delete(hospital);
    }

    private HospitalDTO mapToDTO(Hospital hospital) {
        HospitalDTO dto = new HospitalDTO();
        dto.setId(hospital.getId());
        dto.setName(hospital.getName());
        dto.setLocationId(hospital.getLocation() != null ? hospital.getLocation().getId() : null);
        dto.setLocationName(hospital.getLocation() != null ? hospital.getLocation().getName() : null);
        dto.setCreatedAt(hospital.getCreatedAt());
        dto.setUpdatedAt(hospital.getUpdatedAt());
        return dto;
    }
}
