package com.mediLinkAI.api;

import com.mediLinkAI.dto.MedicalTestDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Diagnostic.MedicalTestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/tests")
public class MedicalTestAPI {

    @Autowired
    private MedicalTestService medicalTestService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<MedicalTestDTO> createTest(@RequestBody @Valid MedicalTestDTO dto) throws MediLinkAI {
        MedicalTestDTO created = medicalTestService.createTest(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/view/{id}", produces = "application/json")
    public ResponseEntity<MedicalTestDTO> getTest(@PathVariable UUID id) throws MediLinkAI {
        MedicalTestDTO test = medicalTestService.getTestById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<MedicalTestDTO>> getAllTests() {
        List<MedicalTestDTO> tests = medicalTestService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<List<MedicalTestDTO>> searchTests(@RequestParam String name) {
        List<MedicalTestDTO> tests = medicalTestService.searchTestsByName(name);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @GetMapping(value = "/by-category/{category}", produces = "application/json")
    public ResponseEntity<List<MedicalTestDTO>> getTestsByCategory(@PathVariable String category) {
        List<MedicalTestDTO> tests = medicalTestService.getTestsByCategory(category);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<MedicalTestDTO> updateTest(@PathVariable UUID id,
            @RequestBody @Valid MedicalTestDTO dto) throws MediLinkAI {
        MedicalTestDTO updated = medicalTestService.updateTest(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteTest(@PathVariable UUID id) throws MediLinkAI {
        medicalTestService.deleteTest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
