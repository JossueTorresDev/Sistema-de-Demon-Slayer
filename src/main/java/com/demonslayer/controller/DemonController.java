package com.demonslayer.controller;

import com.demonslayer.dto.DemonDto;
import com.demonslayer.model.DemonRankEnum;
import com.demonslayer.service.DemonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/demons")
@CrossOrigin(origins = "*")
public class DemonController {

    @Autowired
    private DemonService demonService;

    @GetMapping
    public ResponseEntity<List<DemonDto>> getAllDemons() {
        List<DemonDto> demons = demonService.getAllDemons();
        return ResponseEntity.ok(demons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemonDto> getDemonById(@PathVariable UUID id) {
        DemonDto demon = demonService.getDemonById(id);
        return ResponseEntity.ok(demon);
    }

    @PostMapping
    public ResponseEntity<DemonDto> createDemon(@Valid @RequestBody DemonDto demonDto) {
        DemonDto createdDemon = demonService.createDemon(demonDto);
        return new ResponseEntity<>(createdDemon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemonDto> updateDemon(
            @PathVariable UUID id, 
            @Valid @RequestBody DemonDto demonDto) {
        DemonDto updatedDemon = demonService.updateDemon(id, demonDto);
        return ResponseEntity.ok(updatedDemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemon(@PathVariable UUID id) {
        demonService.deleteDemon(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreDemon(@PathVariable UUID id) {
        demonService.restoreDemon(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rank/{rank}")
    public ResponseEntity<List<DemonDto>> getDemonsByRank(@PathVariable DemonRankEnum rank) {
        List<DemonDto> demons = demonService.getDemonsByRank(rank);
        return ResponseEntity.ok(demons);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DemonDto>> searchDemonsByName(@RequestParam String name) {
        List<DemonDto> demons = demonService.searchDemonsByName(name);
        return ResponseEntity.ok(demons);
    }

    @GetMapping("/moons")
    public ResponseEntity<List<DemonDto>> getMoonDemons() {
        List<DemonDto> moonDemons = demonService.getMoonDemons();
        return ResponseEntity.ok(moonDemons);
    }
}