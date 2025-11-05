package com.demonslayer.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "breathing_styles")
public class BreathingStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private String origin;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "character_breathing_styles",
        joinColumns = @JoinColumn(name = "breathing_style_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<Character> characters;

    @OneToMany(mappedBy = "breathingStyle", cascade = CascadeType.ALL)
    private Set<Technique> techniques;

    // Constructors
    public BreathingStyle() {}

    public BreathingStyle(String name, String origin, String description) {
        this.name = name;
        this.origin = origin;
        this.description = description;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<Character> getCharacters() { return characters; }
    public void setCharacters(Set<Character> characters) { this.characters = characters; }

    public Set<Technique> getTechniques() { return techniques; }
    public void setTechniques(Set<Technique> techniques) { this.techniques = techniques; }
}