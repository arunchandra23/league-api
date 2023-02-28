package com.woxsen.leagueapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
