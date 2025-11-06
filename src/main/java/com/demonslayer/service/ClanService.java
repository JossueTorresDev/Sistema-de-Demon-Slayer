package com.demonslayer.service;

import com.demonslayer.dto.ClanDTO;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.Clan;
import com.demonslayer.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;

    public List<ClanDTO> listarActivos() {
        return clanRepository.findByEliminadoFalse()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ClanDTO> listarEliminados() {
        return clanRepository.findByEliminadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClanDTO obtenerPorId(Long id) {
        Clan clan = clanRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clan no encontrado con ID: " + id));
        return convertToDTO(clan);
    }

    public ClanDTO crear(ClanDTO clanDTO) {
        Clan clan = convertToEntity(clanDTO);
        clan.setEliminado(false);
        Clan savedClan = clanRepository.save(clan);
        return convertToDTO(savedClan);
    }

    public ClanDTO actualizar(Long id, ClanDTO clanDTO) {
        Clan clan = clanRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clan no encontrado con ID: " + id));
        
        clan.setNombre(clanDTO.getNombre());
        clan.setRegion(clanDTO.getRegion());
        clan.setDescripcion(clanDTO.getDescripcion());
        
        Clan updatedClan = clanRepository.save(clan);
        return convertToDTO(updatedClan);
    }

    public void eliminarLogico(Long id) {
        if (!clanRepository.existsById(id)) {
            throw new ResourceNotFoundException("Clan no encontrado con ID: " + id);
        }
        clanRepository.eliminarLogico(id);
    }

    public void restaurar(Long id) {
        if (!clanRepository.existsById(id)) {
            throw new ResourceNotFoundException("Clan no encontrado con ID: " + id);
        }
        clanRepository.restaurar(id);
    }

    private ClanDTO convertToDTO(Clan clan) {
        return new ClanDTO(
                clan.getId(),
                clan.getNombre(),
                clan.getRegion(),
                clan.getDescripcion(),
                clan.getEliminado()
        );
    }

    private Clan convertToEntity(ClanDTO clanDTO) {
        Clan clan = new Clan();
        clan.setNombre(clanDTO.getNombre());
        clan.setRegion(clanDTO.getRegion());
        clan.setDescripcion(clanDTO.getDescripcion());
        return clan;
    }
}