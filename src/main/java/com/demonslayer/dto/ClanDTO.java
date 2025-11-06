package com.demonslayer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClanDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 100, message = "La región no puede exceder 100 caracteres")
    private String region;

    private String descripcion;
    private Boolean eliminado;

    // Constructors
    public ClanDTO() {}

    public ClanDTO(Long id, String nombre, String region, String descripcion, Boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getEliminado() { return eliminado; }
    public void setEliminado(Boolean eliminado) { this.eliminado = eliminado; }
}