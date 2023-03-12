package com.woxsen.leagueapi.payload.response;

import java.time.LocalDate;
import java.util.UUID;

import com.woxsen.leagueapi.entity.Arena;
import com.woxsen.leagueapi.entity.Payment;
import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.utils.BookingStatus;
import com.woxsen.leagueapi.utils.Status;

import lombok.Data;

@Data
public class BookingsByUserResponse {

    private UUID id;


    private LocalDate date;


    private BookingStatus bookingStatus;

    private User user;



    private Arena arena;

    private Slots slot;

    private Payment payment;

    private Status status;
    private boolean activeIndex;

}
