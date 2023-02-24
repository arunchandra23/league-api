package com.woxsen.leagueapi.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.woxsen.leagueapi.utils.ArenaTypes;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
