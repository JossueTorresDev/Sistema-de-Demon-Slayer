package com.demonslayer.controller;

import com.demonslayer.dto.RangoDTO;
import com.demonslayer.service.RangoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rangos")
@CrossOrigin(origins = "*")
public class RangoController {

    @Autowired
    private RangoService rangoService;

    @GetMapping
    public ResponseEntity<List<RangoDTO>> listarActivos() {
        List<RangoDTO> rangos = rangoService.listarActivos();
        return ResponseEntity.ok(rangos);
    }

    @GetMapping("/eliminados")
    public ResponseEntity<List<RangoDTO>> listarEliminados() {
        List<RangoDTO> rangos = rangoService.listarEliminados();
        return ResponseEntity.ok(rangos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RangoDTO> obtenerPorId(@PathVariable Long id) {
        RangoDTO rango = rangoService.obtenerPorId(id);
        return ResponseEntity.ok(rango);
    }

    @PostMapping
    public ResponseEntity<RangoDTO> crear(@Valid @RequestBody RangoDTO rangoDTO) {
        RangoDTO nuevoRango = rangoService.crear(rangoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRango);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RangoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RangoDTO rangoDTO) {
        RangoDTO rangoActualizado = rangoService.actualizar(id, rangoDTO);
        return ResponseEntity.ok(rangoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Long id) {
        rangoService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<Void> restaurar(@PathVariable Long id) {
        rangoService.restaurar(id);
        return ResponseEntity.ok().build();
    }
}