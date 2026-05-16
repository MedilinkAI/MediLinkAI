package com.mediLinkAI.repository.Doctor;

import com.mediLinkAI.entity.Doctor.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, UUID> {
    List<DoctorSpecialization> findByDoctorProfileId(UUID doctorProfileId);
    Optional<DoctorSpecialization> findByDoctorProfileIdAndSpecializationId(UUID doctorProfileId, UUID specializationId);
    boolean existsByDoctorProfileIdAndSpecializationId(UUID doctorProfileId, UUID specializationId);
    void deleteByDoctorProfileId(UUID doctorProfileId);
}
