package com.woxsen.leagueapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDetailsResponse {
    private UUID bookingId;
    private String bookingDate;
    private String arena;
    private String slot;
    private String paymentStatus;

    private String extended;
    private boolean extendable;

}
