package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Arena;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, UUID> {
    List<Arena> findAllByActiveIndex(boolean b);

    List<Arena> findArenaSlotsById(UUID arenaId);
    Arena findByIdAndActiveIndexOrderBySlotsStartTimeDesc(UUID arenaId, boolean b);

    Arena findByIdAndActiveIndex(UUID arenaId, boolean b);
}
