package com.demonslayer.controller;

import com.demonslayer.dto.BreathingStyleDto;
import com.demonslayer.service.BreathingStyleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/breathing-styles")
@CrossOrigin(origins = "*")
public class BreathingStyleController {

    @Autowired
    private BreathingStyleService breathingStyleService;

    @GetMapping
    public ResponseEntity<List<BreathingStyleDto>> getAllBreathingStyles() {
        List<BreathingStyleDto> breathingStyles = breathingStyleService.getAllBreathingStyles();
        return ResponseEntity.ok(breathingStyles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreathingStyleDto> getBreathingStyleById(@PathVariable UUID id) {
        BreathingStyleDto breathingStyle = breathingStyleService.getBreathingStyleById(id);
        return ResponseEntity.ok(breathingStyle);
    }

    @PostMapping
    public ResponseEntity<BreathingStyleDto> createBreathingStyle(@Valid @RequestBody BreathingStyleDto breathingStyleDto) {
        BreathingStyleDto createdBreathingStyle = breathingStyleService.createBreathingStyle(breathingStyleDto);
        return new ResponseEntity<>(createdBreathingStyle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreathingStyleDto> updateBreathingStyle(
            @PathVariable UUID id, 
            @Valid @RequestBody BreathingStyleDto breathingStyleDto) {
        BreathingStyleDto updatedBreathingStyle = breathingStyleService.updateBreathingStyle(id, breathingStyleDto);
        return ResponseEntity.ok(updatedBreathingStyle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreathingStyle(@PathVariable UUID id) {
        breathingStyleService.deleteBreathingStyle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreBreathingStyle(@PathVariable UUID id) {
        breathingStyleService.restoreBreathingStyle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BreathingStyleDto>> searchBreathingStylesByName(@RequestParam String name) {
        List<BreathingStyleDto> breathingStyles = breathingStyleService.searchBreathingStylesByName(name);
        return ResponseEntity.ok(breathingStyles);
    }
}