package com.mediLinkAI.api;

import com.mediLinkAI.dto.DoctorProfileDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Doctor.DoctorProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor-profiles")
public class DoctorProfileAPI {

    @Autowired
    private DoctorProfileService doctorProfileService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<DoctorProfileDTO> createProfile(@RequestBody @Valid DoctorProfileDTO dto) throws MediLinkAI {
        DoctorProfileDTO created = doctorProfileService.createProfile(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view/{userId}", produces = "application/json")
    public ResponseEntity<DoctorProfileDTO> getProfile(@PathVariable UUID userId) throws MediLinkAI {
        DoctorProfileDTO profile = doctorProfileService.getProfileByUserId(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{userId}", produces = "application/json")
    public ResponseEntity<DoctorProfileDTO> updateProfile(@PathVariable UUID userId,
            @RequestBody @Valid DoctorProfileDTO dto) throws MediLinkAI {
        DoctorProfileDTO updated = doctorProfileService.updateProfile(userId, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{userId}", produces = "application/json")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID userId) throws MediLinkAI {
        doctorProfileService.deleteProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
