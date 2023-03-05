package com.woxsen.leagueapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Slots;
@Repository
public interface SlotsRepository extends JpaRepository<Slots, UUID> {
    List<Slots> findAllByActiveIndex(boolean b);

    Slots findByIdAndActiveIndex(UUID slotId, boolean b);

    boolean existsByIdAndActiveIndex(UUID slotId, boolean b);

    List<Slots> findAllByActiveIndexAndIsPaid(boolean b, boolean b1);
}
