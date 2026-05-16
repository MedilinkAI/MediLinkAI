package com.mediLinkAI.api;

import com.mediLinkAI.dto.HospitalDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Master.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/hospitals")
public class HospitalAPI {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<HospitalDTO> createHospital(@RequestBody @Valid HospitalDTO dto) throws MediLinkAI {
        HospitalDTO created = hospitalService.createHospital(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view/{id}", produces = "application/json")
    public ResponseEntity<HospitalDTO> getHospital(@PathVariable UUID id) throws MediLinkAI {
        HospitalDTO hospital = hospitalService.getHospitalById(id);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<HospitalDTO>> getAllHospitals() {
        List<HospitalDTO> hospitals = hospitalService.getAllHospitals();
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<HospitalDTO>> searchHospitals(@RequestParam String name) {
        List<HospitalDTO> hospitals = hospitalService.searchHospitalsByName(name);
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @GetMapping(value = "/by-location/{locationId}", produces = "application/json")
    public ResponseEntity<List<HospitalDTO>> getHospitalsByLocation(@PathVariable UUID locationId) {
        List<HospitalDTO> hospitals = hospitalService.getHospitalsByLocationId(locationId);
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<HospitalDTO> updateHospital(@PathVariable UUID id,
            @RequestBody @Valid HospitalDTO dto) throws MediLinkAI {
        HospitalDTO updated = hospitalService.updateHospital(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteHospital(@PathVariable UUID id) throws MediLinkAI {
        hospitalService.deleteHospital(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
