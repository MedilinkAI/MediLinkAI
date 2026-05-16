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
public class HospitalDTO {
    private UUID id;

    @NotBlank(message = "Hospital name cannot be blank")
    private String name;

    private UUID locationId;

    // Read-only fields for response
    private String locationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
