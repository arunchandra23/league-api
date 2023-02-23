package com.woxsen.leagueapi.repository;

import com.woxsen.leagueapi.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
}
