package com.woxsen.leagueapi.payload.response;

import java.util.List;
import java.util.UUID;

import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.utils.ArenaTypes;
import com.woxsen.leagueapi.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArenaResponse {
	private UUID id;

	private String name;
	private String description;

	private ArenaTypes arenaType;

	private List<SlotsResponseDto> slots;
	private Status status;
	private boolean activeIndex;
}
