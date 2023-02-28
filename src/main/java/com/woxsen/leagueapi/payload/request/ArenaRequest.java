package com.woxsen.leagueapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArenaRequest {
    @NotBlank(message = "Invalid Arena name")
    private String name;

    @NotBlank(message = "Invalid description")
    private String description;

    @NotBlank(message = "Invalid arena type")
    private String arenaType;


}
