package com.mediLinkAI.api;

import com.mediLinkAI.dto.DoctorSpecializationDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Doctor.DoctorSpecializationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor-specializations")
public class DoctorSpecializationAPI {

    @Autowired
    private DoctorSpecializationService doctorSpecializationService;

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<DoctorSpecializationDTO> addSpecialization(@RequestBody @Valid DoctorSpecializationDTO dto) throws MediLinkAI {
        DoctorSpecializationDTO created = doctorSpecializationService.addSpecialization(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/doctor/{doctorProfileId}", produces = "application/json")
    public ResponseEntity<List<DoctorSpecializationDTO>> getSpecializationsByDoctor(@PathVariable UUID doctorProfileId) throws MediLinkAI {
        List<DoctorSpecializationDTO> specializations = doctorSpecializationService.getSpecializationsByDoctorId(doctorProfileId);
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{doctorProfileId}/{specializationId}", produces = "application/json")
    public ResponseEntity<Void> removeSpecialization(@PathVariable UUID doctorProfileId,
            @PathVariable UUID specializationId) throws MediLinkAI {
        doctorSpecializationService.removeSpecialization(doctorProfileId, specializationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
