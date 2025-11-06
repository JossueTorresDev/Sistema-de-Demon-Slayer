package com.demonslayer.controller;

import com.demonslayer.dto.RespiracionDTO;
import com.demonslayer.service.RespiracionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respiraciones")
@CrossOrigin(origins = "*")
public class RespiracionController {

    @Autowired
    private RespiracionService respiracionService;

    @GetMapping
    public ResponseEntity<List<RespiracionDTO>> listarActivos() {
        List<RespiracionDTO> respiraciones = respiracionService.listarActivos();
        return ResponseEntity.ok(respiraciones);
    }

    @GetMapping("/eliminados")
    public ResponseEntity<List<RespiracionDTO>> listarEliminados() {
        List<RespiracionDTO> respiraciones = respiracionService.listarEliminados();
        return ResponseEntity.ok(respiraciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespiracionDTO> obtenerPorId(@PathVariable Long id) {
        RespiracionDTO respiracion = respiracionService.obtenerPorId(id);
        return ResponseEntity.ok(respiracion);
    }

    @PostMapping
    public ResponseEntity<RespiracionDTO> crear(@Valid @RequestBody RespiracionDTO respiracionDTO) {
        RespiracionDTO nuevaRespiracion = respiracionService.crear(respiracionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRespiracion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespiracionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RespiracionDTO respiracionDTO) {
        RespiracionDTO respiracionActualizada = respiracionService.actualizar(id, respiracionDTO);
        return ResponseEntity.ok(respiracionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLogico(@PathVariable Long id) {
        respiracionService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restaurar")
    public ResponseEntity<Void> restaurar(@PathVariable Long id) {
        respiracionService.restaurar(id);
        return ResponseEntity.ok().build();
    }
}