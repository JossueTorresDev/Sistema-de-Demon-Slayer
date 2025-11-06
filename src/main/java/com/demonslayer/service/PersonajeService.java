package com.demonslayer.service;

import com.demonslayer.dto.PersonajeDTO;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.Clan;
import com.demonslayer.model.Personaje;
import com.demonslayer.model.Rango;
import com.demonslayer.model.Respiracion;
import com.demonslayer.repository.ClanRepository;
import com.demonslayer.repository.PersonajeRepository;
import com.demonslayer.repository.RangoRepository;
import com.demonslayer.repository.RespiracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;
    
    @Autowired
    private ClanRepository clanRepository;
    
    @Autowired
    private RangoRepository rangoRepository;
    
    @Autowired
    private RespiracionRepository respiracionRepository;

    public List<PersonajeDTO> listarActivos() {
        return personajeRepository.findByEliminadoFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<PersonajeDTO> listarEliminados() {
        return personajeRepository.findByEliminadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PersonajeDTO obtenerPorId(Long id) {
        Personaje personaje = personajeRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con ID: " + id));
        return convertToDTO(personaje);
    }

    public PersonajeDTO crear(PersonajeDTO personajeDTO) {
        Personaje personaje = convertToEntity(personajeDTO);
        personaje.setEliminado(false);
        Personaje savedPersonaje = personajeRepository.save(personaje);
        return convertToDTO(savedPersonaje);
    }

    public PersonajeDTO actualizar(Long id, PersonajeDTO personajeDTO) {
        Personaje personaje = personajeRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con ID: " + id));
        
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setApellido(personajeDTO.getApellido());
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setGenero(personajeDTO.getGenero());
        personaje.setEsDemonio(personajeDTO.getEsDemonio());
        personaje.setFechaIngreso(personajeDTO.getFechaIngreso());
        personaje.setEstado(personajeDTO.getEstado());
        
        // Actualizar relaciones
        if (personajeDTO.getClanId() != null) {
            Clan clan = clanRepository.findById(personajeDTO.getClanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Clan no encontrado"));
            personaje.setClan(clan);
        } else {
            personaje.setClan(null);
        }
        
        if (personajeDTO.getRangoId() != null) {
            Rango rango = rangoRepository.findById(personajeDTO.getRangoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Rango no encontrado"));
            personaje.setRango(rango);
        } else {
            personaje.setRango(null);
        }
        
        if (personajeDTO.getRespiracionId() != null) {
            Respiracion respiracion = respiracionRepository.findById(personajeDTO.getRespiracionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Respiración no encontrada"));
            personaje.setRespiracion(respiracion);
        } else {
            personaje.setRespiracion(null);
        }
        
        Personaje updatedPersonaje = personajeRepository.save(personaje);
        return convertToDTO(updatedPersonaje);
    }

    public void eliminarLogico(Long id) {
        if (!personajeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Personaje no encontrado con ID: " + id);
        }
        personajeRepository.eliminarLogico(id);
    }

    public void restaurar(Long id) {
        if (!personajeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Personaje no encontrado con ID: " + id);
        }
        personajeRepository.restaurar(id);
    }

    private PersonajeDTO convertToDTO(Personaje personaje) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(personaje.getId());
        dto.setNombre(personaje.getNombre());
        dto.setApellido(personaje.getApellido());
        dto.setEdad(personaje.getEdad());
        dto.setGenero(personaje.getGenero());
        dto.setEsDemonio(personaje.getEsDemonio());
        dto.setFechaIngreso(personaje.getFechaIngreso());
        dto.setEstado(personaje.getEstado());
        dto.setEliminado(personaje.getEliminado());
        
        if (personaje.getClan() != null) {
            dto.setClanId(personaje.getClan().getId());
            dto.setClanNombre(personaje.getClan().getNombre());
        }
        
        if (personaje.getRango() != null) {
            dto.setRangoId(personaje.getRango().getId());
            dto.setRangoNombre(personaje.getRango().getNombre());
        }
        
        if (personaje.getRespiracion() != null) {
            dto.setRespiracionId(personaje.getRespiracion().getId());
            dto.setRespiracionNombre(personaje.getRespiracion().getNombre());
        }
        
        return dto;
    }

    private Personaje convertToEntity(PersonajeDTO personajeDTO) {
        Personaje personaje = new Personaje();
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setApellido(personajeDTO.getApellido());
        personaje.setEdad(personajeDTO.getEdad());
        personaje.setGenero(personajeDTO.getGenero());
        personaje.setEsDemonio(personajeDTO.getEsDemonio());
        personaje.setFechaIngreso(personajeDTO.getFechaIngreso());
        personaje.setEstado(personajeDTO.getEstado());
        
        // Establecer relaciones
        if (personajeDTO.getClanId() != null) {
            Clan clan = clanRepository.findById(personajeDTO.getClanId())
                    .orElseThrow(() -> new ResourceNotFoundException("Clan no encontrado"));
            personaje.setClan(clan);
        }
        
        if (personajeDTO.getRangoId() != null) {
            Rango rango = rangoRepository.findById(personajeDTO.getRangoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Rango no encontrado"));
            personaje.setRango(rango);
        }
        
        if (personajeDTO.getRespiracionId() != null) {
            Respiracion respiracion = respiracionRepository.findById(personajeDTO.getRespiracionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Respiración no encontrada"));
            personaje.setRespiracion(respiracion);
        }
        
        return personaje;
    }
}