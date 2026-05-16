package com.mediLinkAI.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticTestDTO {
    private UUID id;

    @NotNull(message = "Diagnostic profile ID cannot be null")
    private UUID diagnosticProfileId;

    @NotNull(message = "Test ID cannot be null")
    private UUID testId;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    private Boolean isAvailable;

    // Read-only fields for response
    private String testName;
    private String testDescription;
    private LocalDateTime createdAt;
}
