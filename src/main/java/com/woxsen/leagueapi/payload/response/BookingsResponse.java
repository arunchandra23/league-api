package com.woxsen.leagueapi.payload.response;

import java.time.LocalDate;
import java.util.UUID;

import com.woxsen.leagueapi.utils.BookingStatus;
import com.woxsen.leagueapi.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingsResponse {
	private UUID id;

	private LocalDate date;

	private BookingStatus bookingStatus;

	private UserResponse user;

	private ArenaResponse arena;

	private SlotsResponseDto slot;

	private PaymentResponse payment;

	private Status status;
	private boolean activeIndex;
}
