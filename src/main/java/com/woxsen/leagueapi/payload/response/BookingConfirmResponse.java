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
public class BookingConfirmResponse {

    private UUID bookingId;
    private String arena;
    private String slot;
    private String date;
    private String status;

}
