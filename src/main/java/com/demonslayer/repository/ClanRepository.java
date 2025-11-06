package com.demonslayer.repository;

import com.demonslayer.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClanRepository extends JpaRepository<Clan, Long> {
    
    List<Clan> findByEliminadoFalse();
    
    List<Clan> findByEliminadoTrue();
    
    Optional<Clan> findByIdAndEliminadoFalse(Long id);
    
    @Modifying
    @Query("UPDATE Clan c SET c.eliminado = true WHERE c.id = :id")
    void eliminarLogico(@Param("id") Long id);
    
    @Modifying
    @Query("UPDATE Clan c SET c.eliminado = false WHERE c.id = :id")
    void restaurar(@Param("id") Long id);
}