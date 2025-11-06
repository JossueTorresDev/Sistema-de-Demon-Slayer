package com.demonslayer.controller;

import com.demonslayer.dto.PersonajeDTO;
import com.demonslayer.service.PersonajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personajes")
@CrossOrigin(origins = "*")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> listarActivos() {
        List<PersonajeDTO> personajes = personajeService.listarActivos();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/eliminados")
    public ResponseEntity<List<PersonajeDTO>> listarEliminados() {
        List<PersonajeDTO> personajes = personajeService.listarEliminados();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> obtenerPorId(@PathVariable Long id) {
        PersonajeDTO personaje = personajeService.obtenerPorId(id);
        return ResponseEntity.ok(personaje);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> crear(@Valid @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO nuevoPersonaje = personajeService.crear(personajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO personajeActualizado = personajeService.actualizar(id, personajeDTO);
        return ResponseEntity.ok(personajeActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Long id) {
        personajeService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<Void> restaurar(@PathVariable Long id) {
        personajeService.restaurar(id);
        return ResponseEntity.ok().build();
    }
}