package com.demonslayer.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "techniques")
public class Technique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "breathing_style_id", nullable = false)
    private BreathingStyle breathingStyle;

    @Column(nullable = false)
    private String name;

    @Column(name = "form_number")
    private Integer formNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "difficulty_level")
    private Integer difficultyLevel;

    // Constructors
    public Technique() {}

    public Technique(BreathingStyle breathingStyle, String name, Integer formNumber) {
        this.breathingStyle = breathingStyle;
        this.name = name;
        this.formNumber = formNumber;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public BreathingStyle getBreathingStyle() { return breathingStyle; }
    public void setBreathingStyle(BreathingStyle breathingStyle) { this.breathingStyle = breathingStyle; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getFormNumber() { return formNumber; }
    public void setFormNumber(Integer formNumber) { this.formNumber = formNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDifficultyLevel() { return difficultyLevel; }
    public void setDifficultyLevel(Integer difficultyLevel) { this.difficultyLevel = difficultyLevel; }
}