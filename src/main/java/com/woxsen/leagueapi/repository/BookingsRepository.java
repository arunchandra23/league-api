package com.woxsen.leagueapi.repository;

import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, UUID> {

    @Query(nativeQuery = true,value = "SELECT * FROM bookings WHERE arena_id=:arenaId AND booking_date=:date AND booking_status LIKE 'CANCELED' OR booking_status LIKE 'REJECTED' ")
    List<Bookings> getAvailableSlots(@Param("arenaId") UUID arenaId,@Param("date") LocalDate bookingDate);
}
