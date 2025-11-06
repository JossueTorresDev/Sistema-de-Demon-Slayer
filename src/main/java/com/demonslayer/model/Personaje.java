package com.demonslayer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "personajes")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    @Column(length = 100)
    private String apellido;

    private Integer edad;

    @Size(max = 20, message = "El género no puede exceder 20 caracteres")
    @Column(length = 20)
    private String genero;

    @Column(name = "es_demonio")
    private Boolean esDemonio = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clan_id")
    private Clan clan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rango_id")
    private Rango rango;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "respiracion_id")
    private Respiracion respiracion;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Size(max = 50, message = "El estado no puede exceder 50 caracteres")
    @Column(length = 50)
    private String estado = "Vivo";

    @Column(nullable = false)
    private Boolean eliminado = false;

    // Constructors
    public Personaje() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Boolean getEsDemonio() { return esDemonio; }
    public void setEsDemonio(Boolean esDemonio) { this.esDemonio = esDemonio; }

    public Clan getClan() { return clan; }
    public void setClan(Clan clan) { this.clan = clan; }

    public Rango getRango() { return rango; }
    public void setRango(Rango rango) { this.rango = rango; }

    public Respiracion getRespiracion() { return respiracion; }
    public void setRespiracion(Respiracion respiracion) { this.respiracion = respiracion; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Boolean getEliminado() { return eliminado; }
    public void setEliminado(Boolean eliminado) { this.eliminado = eliminado; }
}