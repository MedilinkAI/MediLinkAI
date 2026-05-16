package com.mediLinkAI.services.Diagnostic;

import com.mediLinkAI.dto.DiagnosticTestDTO;
import com.mediLinkAI.entity.Diagnostic.DiagnosticProfile;
import com.mediLinkAI.entity.Diagnostic.DiagnosticTest;
import com.mediLinkAI.entity.Diagnostic.MedicalTest;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Diagnostic.DiagnosticProfileRepository;
import com.mediLinkAI.repository.Diagnostic.DiagnosticTestRepository;
import com.mediLinkAI.repository.Diagnostic.MedicalTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "diagnosticTestService")
@Transactional
public class DiagnosticTestServiceImpl implements DiagnosticTestService {

    @Autowired
    private DiagnosticTestRepository diagnosticTestRepository;

    @Autowired
    private DiagnosticProfileRepository diagnosticProfileRepository;

    @Autowired
    private MedicalTestRepository medicalTestRepository;

    @Override
    public DiagnosticTestDTO addTest(DiagnosticTestDTO dto) throws MediLinkAI {
        DiagnosticProfile diagnosticProfile = diagnosticProfileRepository.findByIdAndIsDeletedFalse(dto.getDiagnosticProfileId())
                .orElseThrow(() -> new MediLinkAI("DIAGNOSTIC_PROFILE_NOT_FOUND"));

        MedicalTest medicalTest = medicalTestRepository.findById(dto.getTestId())
                .orElseThrow(() -> new MediLinkAI("TEST_NOT_FOUND"));

        if (diagnosticTestRepository.existsByDiagnosticProfileIdAndTestId(
                dto.getDiagnosticProfileId(), dto.getTestId())) {
            throw new MediLinkAI("DIAGNOSTIC_TEST_ALREADY_EXISTS");
        }

        DiagnosticTest entity = new DiagnosticTest();
        entity.setDiagnosticProfile(diagnosticProfile);
        entity.setTest(medicalTest);
        entity.setPrice(dto.getPrice());
        entity.setIsAvailable(dto.getIsAvailable() != null ? dto.getIsAvailable() : true);

        entity = diagnosticTestRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosticTestDTO> getTestsByDiagnosticId(UUID diagnosticProfileId) throws MediLinkAI {
        if (!diagnosticProfileRepository.existsById(diagnosticProfileId)) {
            throw new MediLinkAI("DIAGNOSTIC_PROFILE_NOT_FOUND");
        }

        return diagnosticTestRepository.findByDiagnosticProfileId(diagnosticProfileId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosticTestDTO updateTest(UUID id, DiagnosticTestDTO dto) throws MediLinkAI {
        DiagnosticTest entity = diagnosticTestRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("DIAGNOSTIC_TEST_NOT_FOUND"));

        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getIsAvailable() != null) entity.setIsAvailable(dto.getIsAvailable());

        entity = diagnosticTestRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public void removeTest(UUID id) throws MediLinkAI {
        DiagnosticTest entity = diagnosticTestRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("DIAGNOSTIC_TEST_NOT_FOUND"));
        entity.setIsAvailable(false);
        diagnosticTestRepository.save(entity);
    }

    private DiagnosticTestDTO mapToDTO(DiagnosticTest entity) {
        DiagnosticTestDTO dto = new DiagnosticTestDTO();
        dto.setId(entity.getId());
        dto.setDiagnosticProfileId(entity.getDiagnosticProfile() != null ? entity.getDiagnosticProfile().getId() : null);
        dto.setTestId(entity.getTest() != null ? entity.getTest().getId() : null);
        dto.setPrice(entity.getPrice());
        dto.setIsAvailable(entity.getIsAvailable());
        dto.setTestName(entity.getTest() != null ? entity.getTest().getName() : null);
        dto.setTestDescription(entity.getTest() != null ? entity.getTest().getDescription() : null);
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
