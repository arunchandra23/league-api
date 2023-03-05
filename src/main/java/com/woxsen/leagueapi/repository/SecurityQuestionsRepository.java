package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.SecurityQuestions;

@Repository
public interface SecurityQuestionsRepository extends JpaRepository<SecurityQuestions, UUID> {

    List<SecurityQuestions> findAllByActiveIndex(boolean b);

    SecurityQuestions findByIdAndActiveIndex(UUID securityQuestionId, boolean b);
}
