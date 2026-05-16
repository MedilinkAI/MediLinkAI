package com.mediLinkAI.services.Doctor;

import com.mediLinkAI.dto.DoctorHospitalAffiliationDTO;
import com.mediLinkAI.entity.Doctor.DoctorHospitalAffiliation;
import com.mediLinkAI.entity.Doctor.DoctorProfile;
import com.mediLinkAI.entity.Master.Hospital;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Doctor.DoctorHospitalAffiliationRepository;
import com.mediLinkAI.repository.Doctor.DoctorProfileRepository;
import com.mediLinkAI.repository.Master.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "doctorHospitalAffiliationService")
@Transactional
public class DoctorHospitalAffiliationServiceImpl implements DoctorHospitalAffiliationService {

    @Autowired
    private DoctorHospitalAffiliationRepository affiliationRepository;

    @Autowired
    private DoctorProfileRepository doctorProfileRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public DoctorHospitalAffiliationDTO addAffiliation(DoctorHospitalAffiliationDTO dto) throws MediLinkAI {
        DoctorProfile doctorProfile = doctorProfileRepository.findById(dto.getDoctorProfileId())
                .orElseThrow(() -> new MediLinkAI("DOCTOR_PROFILE_NOT_FOUND"));

        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new MediLinkAI("HOSPITAL_NOT_FOUND"));

        if (affiliationRepository.existsByDoctorProfileIdAndHospitalId(
                dto.getDoctorProfileId(), dto.getHospitalId())) {
            throw new MediLinkAI("AFFILIATION_ALREADY_EXISTS");
        }

        DoctorHospitalAffiliation entity = new DoctorHospitalAffiliation();
        entity.setDoctorProfile(doctorProfile);
        entity.setHospital(hospital);
        entity.setConsultationFee(dto.getConsultationFee());
        entity.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : "INR");

        entity = affiliationRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorHospitalAffiliationDTO> getAffiliationsByDoctorId(UUID doctorProfileId) throws MediLinkAI {
        if (!doctorProfileRepository.existsById(doctorProfileId)) {
            throw new MediLinkAI("DOCTOR_PROFILE_NOT_FOUND");
        }

        return affiliationRepository.findByDoctorProfileId(doctorProfileId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorHospitalAffiliationDTO updateAffiliation(UUID id, DoctorHospitalAffiliationDTO dto) throws MediLinkAI {
        DoctorHospitalAffiliation entity = affiliationRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("AFFILIATION_NOT_FOUND"));

        if (dto.getConsultationFee() != null) entity.setConsultationFee(dto.getConsultationFee());
        if (dto.getCurrency() != null) entity.setCurrency(dto.getCurrency());

        entity = affiliationRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public void removeAffiliation(UUID id) throws MediLinkAI {
        DoctorHospitalAffiliation entity = affiliationRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("AFFILIATION_NOT_FOUND"));
        affiliationRepository.delete(entity);
    }

    private DoctorHospitalAffiliationDTO mapToDTO(DoctorHospitalAffiliation entity) {
        DoctorHospitalAffiliationDTO dto = new DoctorHospitalAffiliationDTO();
        dto.setId(entity.getId());
        dto.setDoctorProfileId(entity.getDoctorProfile() != null ? entity.getDoctorProfile().getId() : null);
        dto.setHospitalId(entity.getHospital() != null ? entity.getHospital().getId() : null);
        dto.setConsultationFee(entity.getConsultationFee());
        dto.setCurrency(entity.getCurrency());
        dto.setHospitalName(entity.getHospital() != null ? entity.getHospital().getName() : null);
        dto.setHospitalLocationId(entity.getHospital() != null && entity.getHospital().getLocation() != null
                ? entity.getHospital().getLocation().getId() : null);
        return dto;
    }
}
