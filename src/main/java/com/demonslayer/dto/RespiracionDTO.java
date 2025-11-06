package com.demonslayer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RespiracionDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 100, message = "El tipo no puede exceder 100 caracteres")
    private String tipo;

    @Size(max = 100, message = "El creador no puede exceder 100 caracteres")
    private String creador;

    private String descripcion;
    private Boolean eliminado;

    // Constructors
    public RespiracionDTO() {}

    public RespiracionDTO(Long id, String nombre, String tipo, String creador, String descripcion, Boolean eliminado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.creador = creador;
        this.descripcion = descripcion;
        this.eliminado = eliminado;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCreador() { return creador; }
    public void setCreador(String creador) { this.creador = creador; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getEliminado() { return eliminado; }
    public void setEliminado(Boolean eliminado) { this.eliminado = eliminado; }
}