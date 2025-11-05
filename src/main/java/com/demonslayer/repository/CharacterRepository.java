package com.demonslayer.repository;

import com.demonslayer.model.Character;
import com.demonslayer.model.RankEnum;
import com.demonslayer.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<Character, UUID> {
    
    List<Character> findByRole(RoleEnum role);
    
    List<Character> findByRank(RankEnum rank);
    
    Optional<Character> findByName(String name);
    
    List<Character> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT c FROM Character c WHERE c.role = :role AND c.rank = :rank")
    List<Character> findByRoleAndRank(@Param("role") RoleEnum role, @Param("rank") RankEnum rank);
    
    @Query("SELECT c FROM Character c JOIN c.breathingStyles bs WHERE bs.name = :styleName")
    List<Character> findByBreathingStyleName(@Param("styleName") String styleName);
    
    // Métodos que excluyen eliminados lógicamente
    @Query("SELECT c FROM Character c WHERE c.deleted = false")
    List<Character> findAllActive();
    
    @Query("SELECT c FROM Character c WHERE c.deleted = false AND c.role = :role")
    List<Character> findActiveByRole(@Param("role") RoleEnum role);
    
    @Query("SELECT c FROM Character c WHERE c.deleted = false AND c.rank = :rank")
    List<Character> findActiveByRank(@Param("rank") RankEnum rank);
    
    @Query("SELECT c FROM Character c WHERE c.deleted = false AND c.name LIKE %:name%")
    List<Character> findActiveByNameContainingIgnoreCase(@Param("name") String name);
}