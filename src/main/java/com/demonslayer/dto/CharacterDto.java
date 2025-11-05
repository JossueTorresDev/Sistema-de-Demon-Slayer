package com.demonslayer.dto;

import com.demonslayer.model.RankEnum;
import com.demonslayer.model.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public class CharacterDto {
    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String name;

    private String japaneseName;
    private String alias;
    private LocalDate birthDate;
    private String gender;
    private RoleEnum role;
    private RankEnum rank;
    private Integer heightCm;
    private Integer weightKg;
    private String birthplace;
    private String bio;

    // Constructors
    public CharacterDto() {}

    public CharacterDto(String name, String japaneseName, String alias) {
        this.name = name;
        this.japaneseName = japaneseName;
        this.alias = alias;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJapaneseName() { return japaneseName; }
    public void setJapaneseName(String japaneseName) { this.japaneseName = japaneseName; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public RoleEnum getRole() { return role; }
    public void setRole(RoleEnum role) { this.role = role; }

    public RankEnum getRank() { return rank; }
    public void setRank(RankEnum rank) { this.rank = rank; }

    public Integer getHeightCm() { return heightCm; }
    public void setHeightCm(Integer heightCm) { this.heightCm = heightCm; }

    public Integer getWeightKg() { return weightKg; }
    public void setWeightKg(Integer weightKg) { this.weightKg = weightKg; }

    public String getBirthplace() { return birthplace; }
    public void setBirthplace(String birthplace) { this.birthplace = birthplace; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}