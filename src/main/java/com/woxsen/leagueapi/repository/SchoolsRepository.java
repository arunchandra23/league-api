package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Schools;

@Repository
public interface SchoolsRepository extends JpaRepository<Schools, UUID> {
    Schools findByIdAndActiveIndex(UUID schoolId, boolean b);

    List<Schools> findAllByActiveIndex(boolean b);
}
