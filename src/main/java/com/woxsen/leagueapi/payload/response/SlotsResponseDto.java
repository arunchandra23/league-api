package com.woxsen.leagueapi.payload.response;

import java.util.UUID;

import com.woxsen.leagueapi.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SlotsResponseDto {
	private UUID id;

	private String slot;
	private Status status;

	private boolean activeIndex;
	private boolean isPaid;
}
