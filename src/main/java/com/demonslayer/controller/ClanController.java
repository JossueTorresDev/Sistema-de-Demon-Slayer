package com.demonslayer.controller;

import com.demonslayer.dto.ClanDTO;
import com.demonslayer.service.ClanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clanes")
@CrossOrigin(origins = "*")
public class ClanController {

    @Autowired
    private ClanService clanService;

    @GetMapping
    public ResponseEntity<List<ClanDTO>> listarActivos() {
        List<ClanDTO> clanes = clanService.listarActivos();
        return ResponseEntity.ok(clanes);
    }

    @GetMapping("/eliminados")
    public ResponseEntity<List<ClanDTO>> listarEliminados() {
        List<ClanDTO> clanes = clanService.listarEliminados();
        return ResponseEntity.ok(clanes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClanDTO> obtenerPorId(@PathVariable Long id) {
        ClanDTO clan = clanService.obtenerPorId(id);
        return ResponseEntity.ok(clan);
    }

    @PostMapping
    public ResponseEntity<ClanDTO> crear(@Valid @RequestBody ClanDTO clanDTO) {
        ClanDTO nuevoClan = clanService.crear(clanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoClan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClanDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ClanDTO clanDTO) {
        ClanDTO clanActualizado = clanService.actualizar(id, clanDTO);
        return ResponseEntity.ok(clanActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Long id) {
        clanService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<Void> restaurar(@PathVariable Long id) {
        clanService.restaurar(id);
        return ResponseEntity.ok().build();
    }
}