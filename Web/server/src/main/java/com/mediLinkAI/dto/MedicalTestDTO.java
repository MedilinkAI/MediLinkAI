package com.mediLinkAI.dto;

import com.mediLinkAI.entity.enums.TestCategory;
import com.mediLinkAI.entity.enums.TestType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalTestDTO {
    private UUID id;

    @NotBlank(message = "Test name cannot be blank")
    private String name;

    private String description;

    private TestCategory category;

    private TestType testType;

    private String sampleType;

    private Boolean fastingRequired;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
