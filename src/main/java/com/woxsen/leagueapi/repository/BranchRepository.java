package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Branch;
@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {
    List<Branch> findAllByActiveIndex(boolean b);
}
