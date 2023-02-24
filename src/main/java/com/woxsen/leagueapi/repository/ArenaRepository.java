package com.woxsen.leagueapi.repository;

import com.woxsen.leagueapi.entity.Arena;
import com.woxsen.leagueapi.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, UUID> {
    List<Arena> findAllByActiveIndex(boolean b);

    List<Arena> findArenaSlotsById(UUID arenaId);

    Arena findByIdAndActiveIndex(UUID arenaId, boolean b);
}
