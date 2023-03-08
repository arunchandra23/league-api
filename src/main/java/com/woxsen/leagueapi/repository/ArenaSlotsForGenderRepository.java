package com.woxsen.leagueapi.repository;

import com.woxsen.leagueapi.entity.ArenaSlotsForGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ArenaSlotsForGenderRepository extends JpaRepository<ArenaSlotsForGender, UUID> {

//    @Query(nativeQuery = true,value = "SELECT * FROM arena_slots_gender where")
    List<ArenaSlotsForGender> findAllByArena_id(UUID arenaId);
}
