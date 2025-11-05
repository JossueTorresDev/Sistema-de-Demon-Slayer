package com.demonslayer.dto;

import com.demonslayer.model.DemonRankEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class DemonDto {
    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    private String name;

    private String japaneseName;
    private String alias;
    private String origin;
    private DemonRankEnum demonRank;
    private Integer bloodPower;
    private String weaknesses;
    private String bio;
    private String firstAppearance;

    // Constructors
    public DemonDto() {}

    public DemonDto(String name, String japaneseName, DemonRankEnum demonRank) {
        this.name = name;
        this.japaneseName = japaneseName;
        this.demonRank = demonRank;
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

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public DemonRankEnum getDemonRank() { return demonRank; }
    public void setDemonRank(DemonRankEnum demonRank) { this.demonRank = demonRank; }

    public Integer getBloodPower() { return bloodPower; }
    public void setBloodPower(Integer bloodPower) { this.bloodPower = bloodPower; }

    public String getWeaknesses() { return weaknesses; }
    public void setWeaknesses(String weaknesses) { this.weaknesses = weaknesses; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getFirstAppearance() { return firstAppearance; }
    public void setFirstAppearance(String firstAppearance) { this.firstAppearance = firstAppearance; }
}