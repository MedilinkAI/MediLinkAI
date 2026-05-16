package com.mediLinkAI.repository.Diagnostic;

import com.mediLinkAI.entity.Diagnostic.MedicalTest;
import com.mediLinkAI.entity.enums.TestCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicalTestRepository extends JpaRepository<MedicalTest, UUID> {
    List<MedicalTest> findByNameContainingIgnoreCase(String name);
    List<MedicalTest> findByCategory(TestCategory category);
    boolean existsByName(String name);
}
