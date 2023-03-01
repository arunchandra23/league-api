package com.woxsen.leagueapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.woxsen.leagueapi.payload.response.ArenaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Arena;
import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.ArenaRequest;
import com.woxsen.leagueapi.payload.response.SlotsResponse;
import com.woxsen.leagueapi.repository.ArenaRepository;
import com.woxsen.leagueapi.repository.BookingsRepository;
import com.woxsen.leagueapi.repository.SlotsRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.ArenaTypes;
import com.woxsen.leagueapi.utils.Status;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArenaService {
	@Autowired
	private ArenaRepository arenaRepository;
	@Autowired
	private BookingsRepository bookingsRepository;
	@Autowired
	private SlotsRepository slotsRepository;

	private ModelMapper modelMapper = new ModelMapper();
	public static final String ARENA_NOT_FOUND = "Arena not found with id: ";

	public static List<SlotsResponse> getAvailableSlots(UUID arenaId, ArenaRepository arenaRepository,
			BookingsRepository bookingsRepository) {
		ModelMapper modelMapper = new ModelMapper();
		try {
			List<Slots> allSlots = arenaRepository.findByIdAndActiveIndex(arenaId, true).getSlots();
			List<SlotsResponse> slotsResponses = new ArrayList<>();
			List<Slots> unAvailableSlots = new ArrayList<>();
			List<Bookings> unAvailableBookings = bookingsRepository.getUnAvailableSlots(arenaId, LocalDate.now());
			unAvailableBookings.stream().forEach(x -> {
				unAvailableSlots.add(x.getSlot());
			});
			Arena currentArena=arenaRepository.findByIdAndActiveIndex(arenaId,true);
			allSlots.stream().forEach(x -> {
				SlotsResponse slotsResponse = modelMapper.map(x, SlotsResponse.class);

				switch (LocalDate.now().getDayOfWeek()){
					case MONDAY -> {
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("VOLLEYBALL COURT 1".trim()) ||currentArena.getName().toUpperCase().equals("LAWN TENNIS COURT 1".trim()) ||currentArena.getName().toUpperCase().equals("CROQUET".trim()) )){
							slotsResponse.setForWomen(true);
						}
					}
					case TUESDAY -> {
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("BOX CRICKET".trim()) ||currentArena.getName().toUpperCase().equals("FOOTBALL PITCH 1".trim()) ||currentArena.getName().toUpperCase().equals("KABADDI".trim()) )){
							slotsResponse.setForWomen(true);
						}
					}
					case WEDNESDAY -> {
						log.info(x.getSlot().toUpperCase().trim().trim()+">>"+x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim()));
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("BASKETBALL".trim()) ||currentArena.getName().toUpperCase().equals("SAND VOLLEYBALL".trim()) ||currentArena.getName().toUpperCase().equals("GOLF".trim()) )){
							slotsResponse.setForWomen(true);
						}
					}
					case THURSDAY -> {
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("BOX CRICKET".trim()) ||currentArena.getName().toUpperCase().equals("LAWN TENNIS COURT 1".trim()) ||currentArena.getName().toUpperCase().equals("VOLLEYBALL COURT 1".trim())) ){
							slotsResponse.setForWomen(true);
						}
					}
					case FRIDAY -> {
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("BASKETBALL".trim()) ||currentArena.getName().toUpperCase().equals("CROQUET".trim()) ||currentArena.getName().toUpperCase().equals("FOOTBALL PITCH 1".trim()) )){
							slotsResponse.setForWomen(true);
						}
					}
					case SATURDAY -> {
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("BOX CRICKET".trim()) ||currentArena.getName().toUpperCase().equals("SAND VOLLEYBALL".trim()) ||currentArena.getName().toUpperCase().equals("GOLF".trim()) )){
							slotsResponse.setForWomen(true);
						}
					}
					case SUNDAY -> {
						if((x.getSlot().toUpperCase().trim().equals("7AM - 8AM".trim())||x.getSlot().toUpperCase().trim().equals("7PM - 8PM".trim())) && (currentArena.getName().toUpperCase().equals("VOLLEYBALL COURT 1".trim()) ||currentArena.getName().toUpperCase().equals("LAWN TENNIS COURT 1".trim()) ||currentArena.getName().toUpperCase().equals("KABADDI".trim())) ){
							slotsResponse.setForWomen(true);
						}
					}
				}
				if (unAvailableSlots.contains(x)) {
					slotsResponse.setAvailable(false);
				} else {
					slotsResponse.setAvailable(true);
				}
				slotsResponses.add(slotsResponse);
			});
			return slotsResponses;
		} catch (Exception e) {
			throw new ResourceNotFoundException(ARENA_NOT_FOUND + arenaId);
		}
	}

	public ApiResponse addArena(ArenaRequest arenaRequest) {
		Arena arena = Arena.builder().arenaType(ArenaTypes.valueOf(arenaRequest.getArenaType()))
				.activeIndex(Boolean.TRUE).name(arenaRequest.getName()).description(arenaRequest.getDescription())
				.status(Status.ACTIVE).build();
		Arena save = arenaRepository.save(arena);
		ApiResponse apiResponse = ApiResponse.builder().data(save).errors(new ArrayList<>())
				.message(AppConstants.INSERT_SUCCESS).success(Boolean.TRUE).status(HttpStatus.CREATED).build();
		return apiResponse;
	}

	public ApiResponse getAllArenas() {
		List<Arena> allByActiveIndex = arenaRepository.findAllByActiveIndex(true);
//		ArenaResponse
		ApiResponse apiResponse = ApiResponse.builder().data(allByActiveIndex).errors(new ArrayList<>())
				.message(AppConstants.RETRIEVAL_SUCCESS).success(Boolean.TRUE).status(HttpStatus.OK).build();
		return apiResponse;
	}

	public ApiResponse addSlotToArena(UUID arenaId, UUID slotId) {
		Arena arena = arenaRepository.findByIdAndActiveIndex(arenaId, true);
		Slots slot = slotsRepository.findByIdAndActiveIndex(slotId, true);
		if (arena.getSlots().contains(slot)) {
			throw new BadRequestException("Slot with id: " + slotId + " already exists in arena with id: " + arenaId);
		}
		arena.getSlots().add(slot);
		Arena save = arenaRepository.save(arena);
		ApiResponse apiResponse = ApiResponse.builder().data(save).errors(new ArrayList<>())
				.message(AppConstants.INSERT_SUCCESS).success(Boolean.TRUE).status(HttpStatus.CREATED).build();
		return apiResponse;
	}

	public ApiResponse getSlotsByArena(UUID arenaId) {
		List<SlotsResponse> availableSlots = getAvailableSlots(arenaId, arenaRepository, bookingsRepository);
		ApiResponse apiResponse = ApiResponse.builder().data(availableSlots).errors(new ArrayList<>())
				.message(AppConstants.RETRIEVAL_SUCCESS).success(Boolean.TRUE).status(HttpStatus.OK).build();
		return apiResponse;
	}
}
