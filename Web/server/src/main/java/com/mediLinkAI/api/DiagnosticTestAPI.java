package com.mediLinkAI.api;

import com.mediLinkAI.dto.DiagnosticTestDTO;
import com.mediLinkAI.exception.MediLinkAI;
import com.mediLinkAI.services.Diagnostic.DiagnosticTestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/diagnostic-tests")
public class DiagnosticTestAPI {

    @Autowired
    private DiagnosticTestService diagnosticTestService;

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<DiagnosticTestDTO> addTest(@RequestBody @Valid DiagnosticTestDTO dto) throws MediLinkAI {
        DiagnosticTestDTO created = diagnosticTestService.addTest(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping(value = "/diagnostic/{diagnosticProfileId}", produces = "application/json")
    public ResponseEntity<List<DiagnosticTestDTO>> getTestsByDiagnostic(@PathVariable UUID diagnosticProfileId) throws MediLinkAI {
        List<DiagnosticTestDTO> tests = diagnosticTestService.getTestsByDiagnosticId(diagnosticProfileId);
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<DiagnosticTestDTO> updateTest(@PathVariable UUID id,
            @RequestBody @Valid DiagnosticTestDTO dto) throws MediLinkAI {
        DiagnosticTestDTO updated = diagnosticTestService.updateTest(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}", produces = "application/json")
    public ResponseEntity<Void> removeTest(@PathVariable UUID id) throws MediLinkAI {
        diagnosticTestService.removeTest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
