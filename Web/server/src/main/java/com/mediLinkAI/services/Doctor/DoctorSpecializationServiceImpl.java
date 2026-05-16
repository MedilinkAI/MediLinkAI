package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.DoctorSpecializationDTO;
import com.mediLinkAI.entity.Doctor.DoctorProfile;
import com.mediLinkAI.entity.Doctor.DoctorSpecialization;
import com.mediLinkAI.entity.Doctor.Specialization;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Doctor.DoctorProfileRepository;
import com.mediLinkAI.repository.Doctor.DoctorSpecializationRepository;
import com.mediLinkAI.repository.Doctor.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "doctorSpecializationService")
@Transactional
public class DoctorSpecializationServiceImpl implements DoctorSpecializationService {

    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public DoctorSpecializationDTO addSpecialization(DoctorSpecializationDTO dto) throws MediLinkAI {
        DoctorProfile doctorProfile = doctorProfileRepository.findById(dto.getDoctorProfileId())
                .orElseThrow(() -> new MediLinkAI("DOCTOR_PROFILE_NOT_FOUND"));

        Specialization specialization = specializationRepository.findById(dto.getSpecializationId())
                .orElseThrow(() -> new MediLinkAI("SPECIALIZATION_NOT_FOUND"));

        if (doctorSpecializationRepository.existsByDoctorProfileIdAndSpecializationId(
                dto.getDoctorProfileId(), dto.getSpecializationId())) {
            throw new MediLinkAI("DOCTOR_SPECIALIZATION_ALREADY_EXISTS");
        }

        DoctorSpecialization entity = new DoctorSpecialization();
        entity.setDoctorProfile(doctorProfile);
        entity.setSpecialization(specialization);

        entity = doctorSpecializationRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorSpecializationDTO> getSpecializationsByDoctorId(UUID doctorProfileId) throws MediLinkAI {
        if (!doctorProfileRepository.existsById(doctorProfileId)) {
            throw new MediLinkAI("DOCTOR_PROFILE_NOT_FOUND");
        }

        return doctorSpecializationRepository.findByDoctorProfileId(doctorProfileId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeSpecialization(UUID doctorProfileId, UUID specializationId) throws MediLinkAI {
        DoctorSpecialization entity = doctorSpecializationRepository
                .findByDoctorProfileIdAndSpecializationId(doctorProfileId, specializationId)
                .orElseThrow(() -> new MediLinkAI("DOCTOR_SPECIALIZATION_NOT_FOUND"));

        doctorSpecializationRepository.delete(entity);
    }

    private DoctorSpecializationDTO mapToDTO(DoctorSpecialization entity) {
        DoctorSpecializationDTO dto = new DoctorSpecializationDTO();
        dto.setId(entity.getId());
        dto.setDoctorProfileId(entity.getDoctorProfile() != null ? entity.getDoctorProfile().getId() : null);
        dto.setSpecializationId(entity.getSpecialization() != null ? entity.getSpecialization().getId() : null);
        dto.setSpecializationName(entity.getSpecialization() != null ? entity.getSpecialization().getName() : null);
        dto.setSpecializationDescription(entity.getSpecialization() != null ? entity.getSpecialization().getDescription() : null);
        return dto;
    }
}
