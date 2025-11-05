package com.demonslayer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class TechniqueDto {
    private UUID id;

    @NotNull(message = "El ID del estilo de respiración es obligatorio")
    private UUID breathingStyleId;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String name;

    private Integer formNumber;
    private String description;
    private Integer difficultyLevel;

    // Constructors
    public TechniqueDto() {}

    public TechniqueDto(UUID breathingStyleId, String name, Integer formNumber) {
        this.breathingStyleId = breathingStyleId;
        this.name = name;
        this.formNumber = formNumber;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getBreathingStyleId() { return breathingStyleId; }
    public void setBreathingStyleId(UUID breathingStyleId) { this.breathingStyleId = breathingStyleId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getFormNumber() { return formNumber; }
    public void setFormNumber(Integer formNumber) { this.formNumber = formNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(Integer difficultyLevel) { this.difficultyLevel = difficultyLevel; }
}