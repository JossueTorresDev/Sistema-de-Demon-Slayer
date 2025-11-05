package com.demonslayer.service;

import com.demonslayer.dto.BreathingStyleDto;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.BreathingStyle;
import com.demonslayer.repository.BreathingStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BreathingStyleService {

    @Autowired
    private BreathingStyleRepository breathingStyleRepository;

    public List<BreathingStyleDto> getAllBreathingStyles() {
        return breathingStyleRepository.findAllActive().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BreathingStyleDto getBreathingStyleById(UUID id) {
        BreathingStyle breathingStyle = breathingStyleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estilo de respiración no encontrado con ID: " + id));
        return convertToDto(breathingStyle);
    }

    public BreathingStyleDto createBreathingStyle(BreathingStyleDto breathingStyleDto) {
        BreathingStyle breathingStyle = convertToEntity(breathingStyleDto);
        BreathingStyle savedBreathingStyle = breathingStyleRepository.save(breathingStyle);
        return convertToDto(savedBreathingStyle);
    }

    public BreathingStyleDto updateBreathingStyle(UUID id, BreathingStyleDto breathingStyleDto) {
        BreathingStyle existingBreathingStyle = breathingStyleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estilo de respiración no encontrado con ID: " + id));

        updateBreathingStyleFields(existingBreathingStyle, breathingStyleDto);
        BreathingStyle updatedBreathingStyle = breathingStyleRepository.save(existingBreathingStyle);
        return convertToDto(updatedBreathingStyle);
    }

    public void deleteBreathingStyle(UUID id) {
        BreathingStyle breathingStyle = breathingStyleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estilo de respiración no encontrado con ID: " + id));
        breathingStyle.setDeleted(true);
        breathingStyleRepository.save(breathingStyle);
    }

    public void restoreBreathingStyle(UUID id) {
        BreathingStyle breathingStyle = breathingStyleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estilo de respiración no encontrado con ID: " + id));
        breathingStyle.setDeleted(false);
        breathingStyleRepository.save(breathingStyle);
    }

    public List<BreathingStyleDto> searchBreathingStylesByName(String name) {
        return breathingStyleRepository.findActiveByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BreathingStyleDto convertToDto(BreathingStyle breathingStyle) {
        BreathingStyleDto dto = new BreathingStyleDto();
        dto.setId(breathingStyle.getId());
        dto.setName(breathingStyle.getName());
        dto.setOrigin(breathingStyle.getOrigin());
        dto.setDescription(breathingStyle.getDescription());
        return dto;
    }

    private BreathingStyle convertToEntity(BreathingStyleDto dto) {
        BreathingStyle breathingStyle = new BreathingStyle();
        breathingStyle.setName(dto.getName());
        breathingStyle.setOrigin(dto.getOrigin());
        breathingStyle.setDescription(dto.getDescription());
        return breathingStyle;
    }

    private void updateBreathingStyleFields(BreathingStyle breathingStyle, BreathingStyleDto dto) {
        breathingStyle.setName(dto.getName());
        breathingStyle.setOrigin(dto.getOrigin());
        breathingStyle.setDescription(dto.getDescription());
    }
}