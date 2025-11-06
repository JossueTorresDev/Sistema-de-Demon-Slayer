package com.demonslayer.service;

import com.demonslayer.dto.RangoDTO;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.Rango;
import com.demonslayer.repository.RangoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RangoService {

    @Autowired
    private RangoRepository rangoRepository;

    public List<RangoDTO> listarActivos() {
        return rangoRepository.findByEliminadoFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RangoDTO> listarEliminados() {
        return rangoRepository.findByEliminadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RangoDTO obtenerPorId(Long id) {
        Rango rango = rangoRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rango no encontrado con ID: " + id));
        return convertToDTO(rango);
    }

    public RangoDTO crear(RangoDTO rangoDTO) {
        Rango rango = convertToEntity(rangoDTO);
        rango.setEliminado(false);
        Rango savedRango = rangoRepository.save(rango);
        return convertToDTO(savedRango);
    }

    public RangoDTO actualizar(Long id, RangoDTO rangoDTO) {
        Rango rango = rangoRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rango no encontrado con ID: " + id));
        
        rango.setNombre(rangoDTO.getNombre());
        rango.setNivel(rangoDTO.getNivel());
        rango.setDescripcion(rangoDTO.getDescripcion());
        
        Rango updatedRango = rangoRepository.save(rango);
        return convertToDTO(updatedRango);
    }

    public void eliminarLogico(Long id) {
        if (!rangoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rango no encontrado con ID: " + id);
        }
        rangoRepository.eliminarLogico(id);
    }

    public void restaurar(Long id) {
        if (!rangoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rango no encontrado con ID: " + id);
        }
        rangoRepository.restaurar(id);
    }

    private RangoDTO convertToDTO(Rango rango) {
        return new RangoDTO(
                rango.getId(),
                rango.getNombre(),
                rango.getNivel(),
                rango.getDescripcion(),
                rango.getEliminado()
        );
    }

    private Rango convertToEntity(RangoDTO rangoDTO) {
        Rango rango = new Rango();
        rango.setNombre(rangoDTO.getNombre());
        rango.setNivel(rangoDTO.getNivel());
        rango.setDescripcion(rangoDTO.getDescripcion());
        return rango;
    }
}