package com.woxsen.leagueapi.payload.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
    @NotNull(message = "Invalid Arena id")
    private UUID arenaId;

    @NotNull(message = "Invalid slotId")
    private UUID slotId;



}
