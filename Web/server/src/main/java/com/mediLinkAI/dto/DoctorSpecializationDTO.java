package com.mediLinkAI.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSpecializationDTO {
    private UUID id;

    @NotNull(message = "Doctor profile ID cannot be null")
    private UUID doctorProfileId;

    @NotNull(message = "Specialization ID cannot be null")
    private UUID specializationId;

    // Read-only fields for response
    private String specializationName;
    private String specializationDescription;
}
