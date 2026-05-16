package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.SpecializationDTO;
import com.mediLinkAI.entity.Doctor.Specialization;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Doctor.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "specializationService")
@Transactional
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public SpecializationDTO createSpecialization(SpecializationDTO dto) throws MediLinkAI {
        if (specializationRepository.existsByName(dto.getName())) {
            throw new MediLinkAI("SPECIALIZATION_ALREADY_EXISTS");
        }

        Specialization specialization = new Specialization();
        specialization.setName(dto.getName());
        specialization.setDescription(dto.getDescription());

        specialization = specializationRepository.save(specialization);
        return mapToDTO(specialization);
    }

    @Override
    @Transactional(readOnly = true)
    public SpecializationDTO getSpecializationById(UUID id) throws MediLinkAI {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("SPECIALIZATION_NOT_FOUND"));
        return mapToDTO(specialization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpecializationDTO> getAllSpecializations() {
        return specializationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpecializationDTO updateSpecialization(UUID id, SpecializationDTO dto) throws MediLinkAI {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("SPECIALIZATION_NOT_FOUND"));

        if (dto.getName() != null) specialization.setName(dto.getName());
        if (dto.getDescription() != null) specialization.setDescription(dto.getDescription());

        specialization = specializationRepository.save(specialization);
        return mapToDTO(specialization);
    }

    @Override
    public void deleteSpecialization(UUID id) throws MediLinkAI {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("SPECIALIZATION_NOT_FOUND"));
        specializationRepository.delete(specialization);
    }

    private SpecializationDTO mapToDTO(Specialization specialization) {
        SpecializationDTO dto = new SpecializationDTO();
        dto.setId(specialization.getId());
        dto.setName(specialization.getName());
        dto.setDescription(specialization.getDescription());
        dto.setCreatedAt(specialization.getCreatedAt());
        return dto;
    }
}
