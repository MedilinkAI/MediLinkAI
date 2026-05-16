package com.mediLinkAI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticProfileDTO {
    private UUID id;

    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotBlank(message = "Center name cannot be blank")
    private String centerName;

    private String centerImageUrl;

    private UUID locationId;

    private String contactNumber;

    private String accreditation;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
}
