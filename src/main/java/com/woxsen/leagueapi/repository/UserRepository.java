package com.woxsen.leagueapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User findByUserName(String userName);

    User findByIdAndActiveIndex(UUID userId, boolean b);

}
