package com.mediLinkAI.api;

import com.mediLinkAI.dto.DiagnosticProfileDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Diagnostic.DiagnosticProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/diagnostic-profiles")
public class DiagnosticProfileAPI {

    @Autowired
    private DiagnosticProfileService diagnosticProfileService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<DiagnosticProfileDTO> createProfile(@RequestBody @Valid DiagnosticProfileDTO dto) throws MediLinkAI {
        DiagnosticProfileDTO created = diagnosticProfileService.createProfile(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view/{userId}", produces = "application/json")
    public ResponseEntity<DiagnosticProfileDTO> getProfileByUserId(@PathVariable UUID userId) throws MediLinkAI {
        DiagnosticProfileDTO profile = diagnosticProfileService.getProfileByUserId(userId);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @GetMapping(value = "/view/profile/{id}", produces = "application/json")
    public ResponseEntity<DiagnosticProfileDTO> getProfileById(@PathVariable UUID id) throws MediLinkAI {
        DiagnosticProfileDTO profile = diagnosticProfileService.getProfileById(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{userId}", produces = "application/json")
    public ResponseEntity<DiagnosticProfileDTO> updateProfile(@PathVariable UUID userId,
            @RequestBody @Valid DiagnosticProfileDTO dto) throws MediLinkAI {
        DiagnosticProfileDTO updated = diagnosticProfileService.updateProfile(userId, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{userId}", produces = "application/json")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID userId) throws MediLinkAI {
        diagnosticProfileService.deleteProfile(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
