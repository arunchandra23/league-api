package com.woxsen.leagueapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
