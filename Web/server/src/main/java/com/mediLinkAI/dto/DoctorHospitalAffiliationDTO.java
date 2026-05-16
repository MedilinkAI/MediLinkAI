package com.mediLinkAI.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorHospitalAffiliationDTO {
    private UUID id;

    @NotNull(message = "Doctor profile ID cannot be null")
    private UUID doctorProfileId;

    @NotNull(message = "Hospital ID cannot be null")
    private UUID hospitalId;

    @NotNull(message = "Consultation fee cannot be null")
    private BigDecimal consultationFee;

    private String currency;

    // Read-only fields for response
    private String hospitalName;
    private UUID hospitalLocationId;
}
