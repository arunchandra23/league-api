package com.woxsen.leagueapi.payload.response;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Builder
public class SchoolResponse {
    private UUID id;
    private String name;
}
