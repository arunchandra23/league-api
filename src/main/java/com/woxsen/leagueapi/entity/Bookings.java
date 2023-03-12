package com.woxsen.leagueapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woxsen.leagueapi.utils.BookingTypes;
import com.woxsen.leagueapi.utils.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class Bookings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "booking_date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private BookingTypes bookingType;

//    @Enumerated(EnumType.STRING)
//    private BookingStatus bookingStatus;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arena_id")
    private Arena arena;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private Slots slot;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;
//    @Enumerated(EnumType.STRING)
//    private PaymentStatus paymentStatus;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "extension_id")
    private Extension extension;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean activeIndex;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @Column(name = "created_date")
    private Timestamp createdDate;
}
