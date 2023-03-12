package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woxsen.leagueapi.entity.ArenaSlotsForGender;

public interface ArenaSlotsForGenderRepository extends JpaRepository<ArenaSlotsForGender, UUID> {

//    @Query(nativeQuery = true,value = "SELECT * FROM arena_slots_gender where")
    List<ArenaSlotsForGender> findAllByArena_id(UUID arenaId);
}
