package com.demonslayer.service;

import com.demonslayer.dto.TechniqueDto;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.BreathingStyle;
import com.demonslayer.model.Technique;
import com.demonslayer.repository.BreathingStyleRepository;
import com.demonslayer.repository.TechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class TechniqueService {

    @Autowired
    private TechniqueRepository techniqueRepository;

    @Autowired
    private BreathingStyleRepository breathingStyleRepository;

    public List<TechniqueDto> getAllTechniques() {
        return techniqueRepository.findAllActive().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TechniqueDto getTechniqueById(UUID id) {
        Technique technique = techniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnica no encontrada con ID: " + id));
        return convertToDto(technique);
    }

    public TechniqueDto createTechnique(TechniqueDto techniqueDto) {
        Technique technique = convertToEntity(techniqueDto);
        Technique savedTechnique = techniqueRepository.save(technique);
        return convertToDto(savedTechnique);
    }

    public TechniqueDto updateTechnique(UUID id, TechniqueDto techniqueDto) {
        Technique existingTechnique = techniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnica no encontrada con ID: " + id));

        updateTechniqueFields(existingTechnique, techniqueDto);
        Technique updatedTechnique = techniqueRepository.save(existingTechnique);
        return convertToDto(updatedTechnique);
    }

    public void deleteTechnique(UUID id) {
        Technique technique = techniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnica no encontrada con ID: " + id));
        technique.setDeleted(true);
        techniqueRepository.save(technique);
    }

    public void restoreTechnique(UUID id) {
        Technique technique = techniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Técnica no encontrada con ID: " + id));
        technique.setDeleted(false);
        techniqueRepository.save(technique);
    }

    public List<TechniqueDto> getTechniquesByBreathingStyle(UUID breathingStyleId) {
        return techniqueRepository.findActiveByBreathingStyleId(breathingStyleId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TechniqueDto> searchTechniquesByName(String name) {
        return techniqueRepository.findActiveByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TechniqueDto convertToDto(Technique technique) {
        TechniqueDto dto = new TechniqueDto();
        dto.setId(technique.getId());
        dto.setBreathingStyleId(technique.getBreathingStyle().getId());
        dto.setName(technique.getName());
        dto.setFormNumber(technique.getFormNumber());
        dto.setDescription(technique.getDescription());
        dto.setDifficultyLevel(technique.getDifficultyLevel());
        return dto;
    }

    private Technique convertToEntity(TechniqueDto dto) {
        Technique technique = new Technique();
        
        BreathingStyle breathingStyle = breathingStyleRepository.findById(dto.getBreathingStyleId())
                .orElseThrow(() -> new ResourceNotFoundException("Estilo de respiración no encontrado con ID: " + dto.getBreathingStyleId()));
        
        technique.setBreathingStyle(breathingStyle);
        technique.setName(dto.getName());
        technique.setFormNumber(dto.getFormNumber());
        technique.setDescription(dto.getDescription());
        technique.setDifficultyLevel(dto.getDifficultyLevel());
        return technique;
    }

    private void updateTechniqueFields(Technique technique, TechniqueDto dto) {
        if (dto.getBreathingStyleId() != null) {
            BreathingStyle breathingStyle = breathingStyleRepository.findById(dto.getBreathingStyleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Estilo de respiración no encontrado con ID: " + dto.getBreathingStyleId()));
            technique.setBreathingStyle(breathingStyle);
        }
        technique.setName(dto.getName());
        technique.setFormNumber(dto.getFormNumber());
        technique.setDescription(dto.getDescription());
        technique.setDifficultyLevel(dto.getDifficultyLevel());
    }
}