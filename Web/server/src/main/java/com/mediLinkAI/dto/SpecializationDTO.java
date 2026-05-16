package com.mediLinkAI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecializationDTO {
    private UUID id;

    @NotBlank(message = "Specialization name cannot be blank")
    private String name;

    private String description;

    private LocalDateTime createdAt;
}
