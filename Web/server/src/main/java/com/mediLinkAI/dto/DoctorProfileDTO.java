package com.mediLinkAI.dto;

import com.mediLinkAI.entity.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDTO {
    private UUID id;

    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    private String profilePhotoUrl;
    
    private UUID locationId;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Highest qualification is required")
    private String highestQualification;

    @NotNull(message = "Experience years is required")
    private Integer experienceYears;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNumber;

    @NotBlank(message = "License authority cannot be blank")
    private String licenseAuthority;
}
