package com.woxsen.leagueapi.payload.response;

import com.woxsen.leagueapi.utils.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchResponse {
    private UUID id;

    private String name;

    private Status status;
    private boolean activeIndex;
}
