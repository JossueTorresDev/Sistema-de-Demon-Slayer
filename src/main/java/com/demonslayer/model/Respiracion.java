package com.demonslayer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "respiraciones")
public class Respiracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 100, message = "El tipo no puede exceder 100 caracteres")
    @Column(length = 100)
    private String tipo;

    @Size(max = 100, message = "El creador no puede exceder 100 caracteres")
    @Column(length = 100)
    private String creador;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Boolean eliminado = false;

    // Constructors
    public Respiracion() {}

    public Respiracion(String nombre, String tipo, String creador, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.creador = creador;
        this.descripcion = descripcion;
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