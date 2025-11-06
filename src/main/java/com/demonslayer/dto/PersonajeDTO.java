package com.demonslayer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class PersonajeDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String apellido;

    private Integer edad;

    @Size(max = 20, message = "El género no puede exceder 20 caracteres")
    private String genero;

    private Boolean esDemonio = false;
    private Long clanId;
    private String clanNombre;
    private Long rangoId;
    private String rangoNombre;
    private Long respiracionId;
    private String respiracionNombre;
    private LocalDate fechaIngreso;

    @Size(max = 50, message = "El estado no puede exceder 50 caracteres")
    private String estado = "Vivo";

    private Boolean eliminado;

    // Constructors
    public PersonajeDTO() {}

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

    public Long getClanId() { return clanId; }
    public void setClanId(Long clanId) { this.clanId = clanId; }

    public String getClanNombre() { return clanNombre; }
    public void setClanNombre(String clanNombre) { this.clanNombre = clanNombre; }

    public Long getRangoId() { return rangoId; }
    public void setRangoId(Long rangoId) { this.rangoId = rangoId; }

    public String getRangoNombre() { return rangoNombre; }
    public void setRangoNombre(String rangoNombre) { this.rangoNombre = rangoNombre; }

    public Long getRespiracionId() { return respiracionId; }
    public void setRespiracionId(Long respiracionId) { this.respiracionId = respiracionId; }

    public String getRespiracionNombre() { return respiracionNombre; }
    public void setRespiracionNombre(String respiracionNombre) { this.respiracionNombre = respiracionNombre; }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Boolean getEliminado() { return eliminado; }
    public void setEliminado(Boolean eliminado) { this.eliminado = eliminado; }
}