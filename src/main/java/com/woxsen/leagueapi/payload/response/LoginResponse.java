package com.woxsen.leagueapi.payload.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
@Builder
public class LoginResponse {
    private String token;
}

