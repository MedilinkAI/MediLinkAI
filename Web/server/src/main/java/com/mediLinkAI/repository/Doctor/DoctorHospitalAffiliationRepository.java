package com.mediLinkAI.repository.Doctor;

import com.mediLinkAI.entity.Doctor.DoctorHospitalAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorHospitalAffiliationRepository extends JpaRepository<DoctorHospitalAffiliation, UUID> {
    List<DoctorHospitalAffiliation> findByDoctorProfileId(UUID doctorProfileId);
    Optional<DoctorHospitalAffiliation> findByDoctorProfileIdAndHospitalId(UUID doctorProfileId, UUID hospitalId);
    boolean existsByDoctorProfileIdAndHospitalId(UUID doctorProfileId, UUID hospitalId);
    void deleteByDoctorProfileId(UUID doctorProfileId);
}
