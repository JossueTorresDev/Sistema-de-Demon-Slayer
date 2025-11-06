package com.demonslayer.repository;

import com.demonslayer.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    
    @Query("SELECT p FROM Personaje p LEFT JOIN FETCH p.clan LEFT JOIN FETCH p.rango LEFT JOIN FETCH p.respiracion WHERE p.eliminado = false")
    List<Personaje> findByEliminadoFalse();
    
    @Query("SELECT p FROM Personaje p LEFT JOIN FETCH p.clan LEFT JOIN FETCH p.rango LEFT JOIN FETCH p.respiracion WHERE p.eliminado = true")
    List<Personaje> findByEliminadoTrue();
    
    @Query("SELECT p FROM Personaje p LEFT JOIN FETCH p.clan LEFT JOIN FETCH p.rango LEFT JOIN FETCH p.respiracion WHERE p.id = :id AND p.eliminado = false")
    Optional<Personaje> findByIdAndEliminadoFalse(@Param("id") Long id);
    
    @Modifying
    @Query("UPDATE Personaje p SET p.eliminado = true WHERE p.id = :id")
    void eliminarLogico(@Param("id") Long id);
    
    @Modifying
    @Query("UPDATE Personaje p SET p.eliminado = false WHERE p.id = :id")
    void restaurar(@Param("id") Long id);
}