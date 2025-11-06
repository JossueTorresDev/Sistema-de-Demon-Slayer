package com.demonslayer.repository;

import com.demonslayer.model.Respiracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespiracionRepository extends JpaRepository<Respiracion, Long> {
    
    List<Respiracion> findByEliminadoFalse();
    
    List<Respiracion> findByEliminadoTrue();
    
    Optional<Respiracion> findByIdAndEliminadoFalse(Long id);
    
    @Modifying
    @Query("UPDATE Respiracion r SET r.eliminado = true WHERE r.id = :id")
    void eliminarLogico(@Param("id") Long id);
    
    @Modifying
    @Query("UPDATE Respiracion r SET r.eliminado = false WHERE r.id = :id")
    void restaurar(@Param("id") Long id);
}