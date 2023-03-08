package com.woxsen.leagueapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woxsen.leagueapi.entity.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, UUID> {

	@Query(nativeQuery = true, value = "SELECT * FROM bookings WHERE arena_id=:arenaId AND booking_date=:date")
	List<Bookings> getUnAvailableSlots(@Param("arenaId") UUID arenaId, @Param("date") LocalDate bookingDate);
	@Query(nativeQuery = true, value = "SELECT * FROM bookings WHERE user_id=:userId AND active_index=1")
	List<Bookings> getBookingsByUser(@Param("userId") UUID userId);

	List<Bookings> findAllByActiveIndex(boolean b);

	Bookings findByIdAndActiveIndex(UUID bookingId, boolean b);

	List<Bookings> findAllByArena_idAndActiveIndex(UUID arenaId, boolean b);

	List<Bookings> findAllByUser_id(UUID userId);

	List<Bookings> findAllByUser_idOrderByCreatedDateDesc(UUID userId);
}
