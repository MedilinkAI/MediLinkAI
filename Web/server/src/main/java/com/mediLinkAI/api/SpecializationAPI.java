package com.mediLinkAI.api;

import com.mediLinkAI.dto.SpecializationDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Doctor.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/specializations")
public class SpecializationAPI {

    @Autowired
    private SpecializationService specializationService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<SpecializationDTO> createSpecialization(@RequestBody @Valid SpecializationDTO dto) throws MediLinkAI {
        SpecializationDTO created = specializationService.createSpecialization(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view/{id}", produces = "application/json")
    public ResponseEntity<SpecializationDTO> getSpecialization(@PathVariable UUID id) throws MediLinkAI {
        SpecializationDTO specialization = specializationService.getSpecializationById(id);
        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<SpecializationDTO>> getAllSpecializations() {
        List<SpecializationDTO> specializations = specializationService.getAllSpecializations();
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<SpecializationDTO> updateSpecialization(@PathVariable UUID id,
            @RequestBody @Valid SpecializationDTO dto) throws MediLinkAI {
        SpecializationDTO updated = specializationService.updateSpecialization(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable UUID id) throws MediLinkAI {
        specializationService.deleteSpecialization(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
