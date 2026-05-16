package com.mediLinkAI.services.Diagnostic;

import com.mediLinkAI.dto.MedicalTestDTO;
import com.mediLinkAI.entity.Diagnostic.MedicalTest;
import com.mediLinkAI.entity.enums.TestCategory;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.repository.Diagnostic.MedicalTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "medicalTestService")
@Transactional
public class MedicalTestServiceImpl implements MedicalTestService {

    @Autowired
    private MedicalTestRepository medicalTestRepository;

    @Override
    public MedicalTestDTO createTest(MedicalTestDTO dto) throws MediLinkAI {
        if (medicalTestRepository.existsByName(dto.getName())) {
            throw new MediLinkAI("TEST_ALREADY_EXISTS");
        }

        MedicalTest test = new MedicalTest();
        test.setName(dto.getName());
        test.setDescription(dto.getDescription());
        test.setCategory(dto.getCategory());
        test.setTestType(dto.getTestType());
        test.setSampleType(dto.getSampleType());
        test.setFastingRequired(dto.getFastingRequired() != null ? dto.getFastingRequired() : false);

        test = medicalTestRepository.save(test);
        return mapToDTO(test);
    }

    @Override
    @Transactional(readOnly = true)
    public MedicalTestDTO getTestById(UUID id) throws MediLinkAI {
        MedicalTest test = medicalTestRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("TEST_NOT_FOUND"));
        return mapToDTO(test);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalTestDTO> getAllTests() {
        return medicalTestRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalTestDTO> searchTestsByName(String name) {
        return medicalTestRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalTestDTO> getTestsByCategory(String category) {
        TestCategory testCategory = TestCategory.valueOf(category.toUpperCase());
        return medicalTestRepository.findByCategory(testCategory).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalTestDTO updateTest(UUID id, MedicalTestDTO dto) throws MediLinkAI {
        MedicalTest test = medicalTestRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("TEST_NOT_FOUND"));

        if (dto.getName() != null) test.setName(dto.getName());
        if (dto.getDescription() != null) test.setDescription(dto.getDescription());
        if (dto.getCategory() != null) test.setCategory(dto.getCategory());
        if (dto.getTestType() != null) test.setTestType(dto.getTestType());
        if (dto.getSampleType() != null) test.setSampleType(dto.getSampleType());
        if (dto.getFastingRequired() != null) test.setFastingRequired(dto.getFastingRequired());

        test = medicalTestRepository.save(test);
        return mapToDTO(test);
    }

    @Override
    public void deleteTest(UUID id) throws MediLinkAI {
        MedicalTest test = medicalTestRepository.findById(id)
                .orElseThrow(() -> new MediLinkAI("TEST_NOT_FOUND"));
        medicalTestRepository.delete(test);
    }

    private MedicalTestDTO mapToDTO(MedicalTest test) {
        MedicalTestDTO dto = new MedicalTestDTO();
        dto.setId(test.getId());
        dto.setName(test.getName());
        dto.setDescription(test.getDescription());
        dto.setCategory(test.getCategory());
        dto.setTestType(test.getTestType());
        dto.setSampleType(test.getSampleType());
        dto.setFastingRequired(test.getFastingRequired());
        dto.setCreatedAt(test.getCreatedAt());
        dto.setUpdatedAt(test.getUpdatedAt());
        return dto;
    }
}
