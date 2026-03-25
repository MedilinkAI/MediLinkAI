package com.mediLinkAI.api;

import com.mediLinkAI.dto.PatientProfileDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Patient.PatientProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patient-profiles")
public class PatientProfileAPI {

    @Autowired
    private PatientProfileService patientProfileService;

    @PostMapping
    public ResponseEntity<PatientProfileDTO> createProfile(@RequestBody PatientProfileDTO dto) throws MediLinkAI {
        PatientProfileDTO created = patientProfileService.createProfile(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PatientProfileDTO> getProfile(@PathVariable UUID userId) throws MediLinkAI {
        PatientProfileDTO profile = patientProfileService.getProfileByUserId(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<PatientProfileDTO> updateProfile(@PathVariable UUID userId,
            @RequestBody PatientProfileDTO dto) throws MediLinkAI {
        PatientProfileDTO updated = patientProfileService.updateProfile(userId, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID userId) throws MediLinkAI {
        patientProfileService.deleteProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
