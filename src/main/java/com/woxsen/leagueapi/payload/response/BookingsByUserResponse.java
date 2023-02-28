package com.woxsen.leagueapi.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woxsen.leagueapi.entity.Arena;
import com.woxsen.leagueapi.entity.Payment;
import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.utils.BookingStatus;
import com.woxsen.leagueapi.utils.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

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
