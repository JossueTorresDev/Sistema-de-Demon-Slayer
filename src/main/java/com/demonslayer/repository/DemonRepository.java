package com.demonslayer.repository;

import com.demonslayer.model.Demon;
import com.demonslayer.model.DemonRankEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DemonRepository extends JpaRepository<Demon, UUID> {
    
    List<Demon> findByDemonRank(DemonRankEnum demonRank);
    
    Optional<Demon> findByName(String name);
    
    List<Demon> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT d FROM Demon d WHERE d.bloodPower >= :minPower")
    List<Demon> findByBloodPowerGreaterThanEqual(@Param("minPower") Integer minPower);
    
    @Query("SELECT d FROM Demon d WHERE d.demonRank IN ('upper_moon', 'lower_moon')")
    List<Demon> findMoonDemons();
    
    // Métodos que excluyen eliminados lógicamente
    @Query("SELECT d FROM Demon d WHERE d.deleted = false")
    List<Demon> findAllActive();
    
    @Query("SELECT d FROM Demon d WHERE d.deleted = false AND d.demonRank = :rank")
    List<Demon> findActiveByDemonRank(@Param("rank") DemonRankEnum rank);
    
    @Query("SELECT d FROM Demon d WHERE d.deleted = false AND d.name LIKE %:name%")
    List<Demon> findActiveByNameContainingIgnoreCase(@Param("name") String name);
    
    @Query("SELECT d FROM Demon d WHERE d.deleted = false AND d.demonRank IN ('upper_moon', 'lower_moon')")
    List<Demon> findActiveMoonDemons();
}