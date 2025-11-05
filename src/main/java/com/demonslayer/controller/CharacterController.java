package com.demonslayer.controller;

import com.demonslayer.dto.CharacterDto;
import com.demonslayer.model.RankEnum;
import com.demonslayer.model.RoleEnum;
import com.demonslayer.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "*")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getAllCharacters() {
        List<CharacterDto> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable UUID id) {
        CharacterDto character = characterService.getCharacterById(id);
        return ResponseEntity.ok(character);
    }

    @PostMapping
    public ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) {
        CharacterDto createdCharacter = characterService.createCharacter(characterDto);
        return new ResponseEntity<>(createdCharacter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDto> updateCharacter(
            @PathVariable UUID id, 
            @Valid @RequestBody CharacterDto characterDto) {
        CharacterDto updatedCharacter = characterService.updateCharacter(id, characterDto);
        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<Void> restoreCharacter(@PathVariable UUID id) {
        characterService.restoreCharacter(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<CharacterDto>> getCharactersByRole(@PathVariable RoleEnum role) {
        List<CharacterDto> characters = characterService.getCharactersByRole(role);
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/rank/{rank}")
    public ResponseEntity<List<CharacterDto>> getCharactersByRank(@PathVariable RankEnum rank) {
        List<CharacterDto> characters = characterService.getCharactersByRank(rank);
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CharacterDto>> searchCharactersByName(@RequestParam String name) {
        List<CharacterDto> characters = characterService.searchCharactersByName(name);
        return ResponseEntity.ok(characters);
    }
}