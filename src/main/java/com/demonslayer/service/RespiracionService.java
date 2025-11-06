package com.demonslayer.service;

import com.demonslayer.dto.RespiracionDTO;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.Respiracion;
import com.demonslayer.repository.RespiracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RespiracionService {

    @Autowired
    private RespiracionRepository respiracionRepository;

    public List<RespiracionDTO> listarActivos() {
        return respiracionRepository.findByEliminadoFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<RespiracionDTO> listarEliminados() {
        return respiracionRepository.findByEliminadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RespiracionDTO obtenerPorId(Long id) {
        Respiracion respiracion = respiracionRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respiración no encontrada con ID: " + id));
        return convertToDTO(respiracion);
    }

    public RespiracionDTO crear(RespiracionDTO respiracionDTO) {
        Respiracion respiracion = convertToEntity(respiracionDTO);
        respiracion.setEliminado(false);
        Respiracion savedRespiracion = respiracionRepository.save(respiracion);
        return convertToDTO(savedRespiracion);
    }

    public RespiracionDTO actualizar(Long id, RespiracionDTO respiracionDTO) {
        Respiracion respiracion = respiracionRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Respiración no encontrada con ID: " + id));
        
        respiracion.setNombre(respiracionDTO.getNombre());
        respiracion.setTipo(respiracionDTO.getTipo());
        respiracion.setCreador(respiracionDTO.getCreador());
        respiracion.setDescripcion(respiracionDTO.getDescripcion());
        
        Respiracion updatedRespiracion = respiracionRepository.save(respiracion);
        return convertToDTO(updatedRespiracion);
    }

    public void eliminarLogico(Long id) {
        if (!respiracionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Respiración no encontrada con ID: " + id);
        }
        respiracionRepository.eliminarLogico(id);
    }

    public void restaurar(Long id) {
        if (!respiracionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Respiración no encontrada con ID: " + id);
        }
        respiracionRepository.restaurar(id);
    }

    private RespiracionDTO convertToDTO(Respiracion respiracion) {
        return new RespiracionDTO(
                respiracion.getId(),
                respiracion.getNombre(),
                respiracion.getTipo(),
                respiracion.getCreador(),
                respiracion.getDescripcion(),
                respiracion.getEliminado()
        );
    }

    private Respiracion convertToEntity(RespiracionDTO respiracionDTO) {
        Respiracion respiracion = new Respiracion();
        respiracion.setNombre(respiracionDTO.getNombre());
        respiracion.setTipo(respiracionDTO.getTipo());
        respiracion.setCreador(respiracionDTO.getCreador());
        respiracion.setDescripcion(respiracionDTO.getDescripcion());
        return respiracion;
    }
}