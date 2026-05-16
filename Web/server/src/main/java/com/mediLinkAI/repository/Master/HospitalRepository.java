package com.mediLinkAI.repository.Master;

import com.mediLinkAI.entity.Master.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
    List<Hospital> findByNameContainingIgnoreCase(String name);
    List<Hospital> findByLocationId(UUID locationId);
}
