package com.demonslayer.repository;

import com.demonslayer.model.Technique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TechniqueRepository extends JpaRepository<Technique, UUID> {
    
    List<Technique> findByBreathingStyleId(UUID breathingStyleId);
    
    Optional<Technique> findByName(String name);
    
    List<Technique> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT t FROM Technique t WHERE t.breathingStyle.id = :styleId AND t.formNumber = :formNumber")
    Optional<Technique> findByBreathingStyleIdAndFormNumber(@Param("styleId") UUID styleId, @Param("formNumber") Integer formNumber);
    
    // Métodos que excluyen eliminados lógicamente
    @Query("SELECT t FROM Technique t WHERE t.deleted = false")
    List<Technique> findAllActive();
    
    @Query("SELECT t FROM Technique t WHERE t.deleted = false AND t.breathingStyle.id = :styleId")
    List<Technique> findActiveByBreathingStyleId(@Param("styleId") UUID styleId);
    
    @Query("SELECT t FROM Technique t WHERE t.deleted = false AND t.name LIKE %:name%")
    List<Technique> findActiveByNameContainingIgnoreCase(@Param("name") String name);
}