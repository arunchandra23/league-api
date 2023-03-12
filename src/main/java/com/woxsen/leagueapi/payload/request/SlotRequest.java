package com.woxsen.leagueapi.payload.request;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotRequest {
    @NotBlank(message = "Invalid slot name")
    @JsonProperty("slotName")
    private String slot;
    @NotNull(message = "Invalid start time")
    @JsonProperty("slotStartTime")
    private Time startTime;

}
