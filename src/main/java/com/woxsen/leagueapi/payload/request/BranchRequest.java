package com.woxsen.leagueapi.payload.request;

import java.util.UUID;

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
public class BranchRequest {
    @NotBlank(message = "In-valid branch name")
    @JsonProperty("branchName")
    private String name;

    @NotNull(message = "In-valid school id")
    private UUID schoolId;

}
