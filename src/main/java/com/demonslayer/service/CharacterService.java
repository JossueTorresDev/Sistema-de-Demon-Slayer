package com.demonslayer.service;

import com.demonslayer.dto.CharacterDto;
import com.demonslayer.exception.ResourceNotFoundException;
import com.demonslayer.model.Character;
import com.demonslayer.model.RankEnum;
import com.demonslayer.model.RoleEnum;
import com.demonslayer.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<CharacterDto> getAllCharacters() {
        return characterRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CharacterDto getCharacterById(UUID id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con ID: " + id));
        return convertToDto(character);
    }

    public CharacterDto createCharacter(CharacterDto characterDto) {
        Character character = convertToEntity(characterDto);
        Character savedCharacter = characterRepository.save(character);
        return convertToDto(savedCharacter);
    }

    public CharacterDto updateCharacter(UUID id, CharacterDto characterDto) {
        Character existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con ID: " + id));

        updateCharacterFields(existingCharacter, characterDto);
        Character updatedCharacter = characterRepository.save(existingCharacter);
        return convertToDto(updatedCharacter);
    }

    public void deleteCharacter(UUID id) {
        if (!characterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Personaje no encontrado con ID: " + id);
        }
        characterRepository.deleteById(id);
    }

    public List<CharacterDto> getCharactersByRole(RoleEnum role) {
        return characterRepository.findByRole(role).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CharacterDto> getCharactersByRank(RankEnum rank) {
        return characterRepository.findByRank(rank).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CharacterDto> searchCharactersByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CharacterDto convertToDto(Character character) {
        CharacterDto dto = new CharacterDto();
        dto.setId(character.getId());
        dto.setName(character.getName());
        dto.setJapaneseName(character.getJapaneseName());
        dto.setAlias(character.getAlias());
        dto.setBirthDate(character.getBirthDate());
        dto.setGender(character.getGender());
        dto.setRole(character.getRole());
        dto.setRank(character.getRank());
        dto.setHeightCm(character.getHeightCm());
        dto.setWeightKg(character.getWeightKg());
        dto.setBirthplace(character.getBirthplace());
        dto.setBio(character.getBio());
        return dto;
    }

    private Character convertToEntity(CharacterDto dto) {
        Character character = new Character();
        character.setName(dto.getName());
        character.setJapaneseName(dto.getJapaneseName());
        character.setAlias(dto.getAlias());
        character.setBirthDate(dto.getBirthDate());
        character.setGender(dto.getGender());
        character.setRole(dto.getRole() != null ? dto.getRole() : RoleEnum.demon_slayer);
        character.setRank(dto.getRank() != null ? dto.getRank() : RankEnum.unranked);
        character.setHeightCm(dto.getHeightCm());
        character.setWeightKg(dto.getWeightKg());
        character.setBirthplace(dto.getBirthplace());
        character.setBio(dto.getBio());
        return character;
    }

    private void updateCharacterFields(Character character, CharacterDto dto) {
        character.setName(dto.getName());
        character.setJapaneseName(dto.getJapaneseName());
        character.setAlias(dto.getAlias());
        character.setBirthDate(dto.getBirthDate());
        character.setGender(dto.getGender());
        character.setRole(dto.getRole());
        character.setRank(dto.getRank());
        character.setHeightCm(dto.getHeightCm());
        character.setWeightKg(dto.getWeightKg());
        character.setBirthplace(dto.getBirthplace());
        character.setBio(dto.getBio());
    }
}