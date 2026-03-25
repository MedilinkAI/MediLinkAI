package com.mediLinkAI.dto;

import com.mediLinkAI.entity.enums.BloodGroup;
import com.mediLinkAI.entity.enums.Gender;
import com.mediLinkAI.entity.enums.PrivacyLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfileDTO {
    private UUID id;
    private UUID userId;
    private String fullName;
    private String profilePhotoUrl;
    private UUID locationId;
    private LocalDate dateOfBirth;
    private Gender gender;
    private BloodGroup bloodGroup;
    private Double heightCm;
    private Double weightKg;
    private String abhaId;
    private Boolean emergencyCardEnabled;
    private PrivacyLevel privacyLevel;
}
