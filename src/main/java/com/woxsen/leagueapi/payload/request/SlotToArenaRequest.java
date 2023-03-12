package com.woxsen.leagueapi.payload.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotToArenaRequest {
    @NotBlank(message = "daysForWomen")
    private List<Integer> daysForWomen;
}
