package com.demonslayer.service;

import com.demonslayer.dto.DemonDto;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.Demon;
import com.demonslayer.model.DemonRankEnum;
import com.demonslayer.repository.DemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemonService {

    @Autowired
    private DemonRepository demonRepository;

    public List<DemonDto> getAllDemons() {
        return demonRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DemonDto getDemonById(UUID id) {
        Demon demon = demonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demonio no encontrado con ID: " + id));
        return convertToDto(demon);
    }

    public DemonDto createDemon(DemonDto demonDto) {
        Demon demon = convertToEntity(demonDto);
        Demon savedDemon = demonRepository.save(demon);
        return convertToDto(savedDemon);
    }

    public DemonDto updateDemon(UUID id, DemonDto demonDto) {
        Demon existingDemon = demonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Demonio no encontrado con ID: " + id));

        updateDemonFields(existingDemon, demonDto);
        Demon updatedDemon = demonRepository.save(existingDemon);
        return convertToDto(updatedDemon);
    }

    public void deleteDemon(UUID id) {
        if (!demonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Demonio no encontrado con ID: " + id);
        }
        demonRepository.deleteById(id);
    }

    public List<DemonDto> getDemonsByRank(DemonRankEnum rank) {
        return demonRepository.findByDemonRank(rank).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DemonDto> searchDemonsByName(String name) {
        return demonRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DemonDto> getMoonDemons() {
        return demonRepository.findMoonDemons().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DemonDto convertToDto(Demon demon) {
        DemonDto dto = new DemonDto();
        dto.setId(demon.getId());
        dto.setName(demon.getName());
        dto.setJapaneseName(demon.getJapaneseName());
        dto.setAlias(demon.getAlias());
        dto.setOrigin(demon.getOrigin());
        dto.setDemonRank(demon.getDemonRank());
        dto.setBloodPower(demon.getBloodPower());
        dto.setWeaknesses(demon.getWeaknesses());
        dto.setBio(demon.getBio());
        dto.setFirstAppearance(demon.getFirstAppearance());
        return dto;
    }

    private Demon convertToEntity(DemonDto dto) {
        Demon demon = new Demon();
        demon.setName(dto.getName());
        demon.setJapaneseName(dto.getJapaneseName());
        demon.setAlias(dto.getAlias());
        demon.setOrigin(dto.getOrigin());
        demon.setDemonRank(dto.getDemonRank() != null ? dto.getDemonRank() : DemonRankEnum.common);
        demon.setBloodPower(dto.getBloodPower());
        demon.setWeaknesses(dto.getWeaknesses());
        demon.setBio(dto.getBio());
        demon.setFirstAppearance(dto.getFirstAppearance());
        return demon;
    }

    private void updateDemonFields(Demon demon, DemonDto dto) {
        demon.setName(dto.getName());
        demon.setJapaneseName(dto.getJapaneseName());
        demon.setAlias(dto.getAlias());
        demon.setOrigin(dto.getOrigin());
        demon.setDemonRank(dto.getDemonRank());
        demon.setBloodPower(dto.getBloodPower());
        demon.setWeaknesses(dto.getWeaknesses());
        demon.setBio(dto.getBio());
        demon.setFirstAppearance(dto.getFirstAppearance());
    }
}