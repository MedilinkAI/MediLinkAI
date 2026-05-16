package com.mediLinkAI.api;

import com.mediLinkAI.dto.DoctorHospitalAffiliationDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Doctor.DoctorHospitalAffiliationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor-affiliations")
public class DoctorHospitalAffiliationAPI {

    @Autowired
    private DoctorHospitalAffiliationService affiliationService;

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<DoctorHospitalAffiliationDTO> addAffiliation(@RequestBody @Valid DoctorHospitalAffiliationDTO dto) throws MediLinkAI {
        DoctorHospitalAffiliationDTO created = affiliationService.addAffiliation(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/doctor/{doctorProfileId}", produces = "application/json")
    public ResponseEntity<List<DoctorHospitalAffiliationDTO>> getAffiliationsByDoctor(@PathVariable UUID doctorProfileId) throws MediLinkAI {
        List<DoctorHospitalAffiliationDTO> affiliations = affiliationService.getAffiliationsByDoctorId(doctorProfileId);
        return new ResponseEntity<>(affiliations, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<DoctorHospitalAffiliationDTO> updateAffiliation(@PathVariable UUID id,
            @RequestBody @Valid DoctorHospitalAffiliationDTO dto) throws MediLinkAI {
        DoctorHospitalAffiliationDTO updated = affiliationService.updateAffiliation(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}", produces = "application/json")
    public ResponseEntity<Void> removeAffiliation(@PathVariable UUID id) throws MediLinkAI {
        affiliationService.removeAffiliation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
