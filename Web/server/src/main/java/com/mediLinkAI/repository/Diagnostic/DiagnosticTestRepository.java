package com.mediLinkAI.repository.Diagnostic;

import com.mediLinkAI.entity.Diagnostic.DiagnosticTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiagnosticTestRepository extends JpaRepository<DiagnosticTest, UUID> {
    List<DiagnosticTest> findByDiagnosticProfileId(UUID diagnosticProfileId);
    Optional<DiagnosticTest> findByDiagnosticProfileIdAndTestId(UUID diagnosticProfileId, UUID testId);
    boolean existsByDiagnosticProfileIdAndTestId(UUID diagnosticProfileId, UUID testId);
    void deleteByDiagnosticProfileId(UUID diagnosticProfileId);
}
