package com.woxsen.leagueapi.payload.response;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woxsen.leagueapi.entity.Bookings;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private UUID id;

    private String amount;

    private String txnid;

    private UserResponse user;


    private Bookings bookings;


    private String mode;

    private String error;

    private String error_message;

    private String unmappedstatus;
    private String key;
    private String productinfo;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String status;

    private boolean activeIndex;
}
