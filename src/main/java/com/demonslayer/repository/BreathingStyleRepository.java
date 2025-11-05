package com.demonslayer.repository;

import com.demonslayer.model.BreathingStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BreathingStyleRepository extends JpaRepository<BreathingStyle, UUID> {
    
    Optional<BreathingStyle> findByName(String name);
    
    List<BreathingStyle> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT bs FROM BreathingStyle bs JOIN bs.characters c WHERE c.id = :characterId")
    List<BreathingStyle> findByCharacterId(@Param("characterId") UUID characterId);
    
    @Query("SELECT bs FROM BreathingStyle bs WHERE SIZE(bs.techniques) > :minTechniques")
    List<BreathingStyle> findByTechniqueCountGreaterThan(@Param("minTechniques") int minTechniques);
}