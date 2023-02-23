package com.woxsen.leagueapi.repository;

import com.woxsen.leagueapi.entity.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArenaRepository extends JpaRepository<Arena, UUID> {
}
