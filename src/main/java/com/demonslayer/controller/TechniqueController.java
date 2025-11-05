package com.demonslayer.controller;

import com.demonslayer.dto.TechniqueDto;
import com.demonslayer.service.TechniqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/techniques")
@CrossOrigin(origins = "*")
public class TechniqueController {

    @Autowired
    private TechniqueService techniqueService;

    @GetMapping
    public ResponseEntity<List<TechniqueDto>> getAllTechniques() {
        List<TechniqueDto> techniques = techniqueService.getAllTechniques();
        return ResponseEntity.ok(techniques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechniqueDto> getTechniqueById(@PathVariable UUID id) {
        TechniqueDto technique = techniqueService.getTechniqueById(id);
        return ResponseEntity.ok(technique);
    }

    @PostMapping
    public ResponseEntity<TechniqueDto> createTechnique(@Valid @RequestBody TechniqueDto techniqueDto) {
        TechniqueDto createdTechnique = techniqueService.createTechnique(techniqueDto);
        return new ResponseEntity<>(createdTechnique, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechniqueDto> updateTechnique(
            @PathVariable UUID id, 
            @Valid @RequestBody TechniqueDto techniqueDto) {
        TechniqueDto updatedTechnique = techniqueService.updateTechnique(id, techniqueDto);
        return ResponseEntity.ok(updatedTechnique);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnique(@PathVariable UUID id) {
        techniqueService.deleteTechnique(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreTechnique(@PathVariable UUID id) {
        techniqueService.restoreTechnique(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/breathing-style/{breathingStyleId}")
    public ResponseEntity<List<TechniqueDto>> getTechniquesByBreathingStyle(@PathVariable UUID breathingStyleId) {
        List<TechniqueDto> techniques = techniqueService.getTechniquesByBreathingStyle(breathingStyleId);
        return ResponseEntity.ok(techniques);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TechniqueDto>> searchTechniquesByName(@RequestParam String name) {
        List<TechniqueDto> techniques = techniqueService.searchTechniquesByName(name);
        return ResponseEntity.ok(techniques);
    }
}