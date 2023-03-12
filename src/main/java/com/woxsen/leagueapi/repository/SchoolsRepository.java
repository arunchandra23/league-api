package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import com.woxsen.leagueapi.entity.Schools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolsRepository extends JpaRepository<Schools, UUID> {
    Schools findByIdAndActiveIndex(UUID schoolId, boolean b);

    List<Schools> findAllByActiveIndex(boolean b);
}
