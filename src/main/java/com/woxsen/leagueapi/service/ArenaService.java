package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.Arena;
import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.ArenaRequest;
import com.woxsen.leagueapi.repository.ArenaRepository;
import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.repository.SlotsRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.ArenaTypes;
import com.woxsen.leagueapi.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArenaService {
    @Autowired
    private ArenaRepository arenaRepository;
    @Autowired
    private SlotsRepository slotsRepository;
    public static final String ARENA_NOT_FOUND="Arena not found with id: ";
    public ApiResponse addArena(ArenaRequest arenaRequest) {
        Arena arena=Arena.builder()
                .arenaType(ArenaTypes.valueOf(arenaRequest.getArenaType()))
                .activeIndex(Boolean.TRUE)
                .name(arenaRequest.getName())
                .description(arenaRequest.getDescription())
                .status(Status.ACTIVE)
                .build();
        Arena save = arenaRepository.save(arena);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;
    }

    public ApiResponse getAllArenas() {
        List<Arena> allByActiveIndex = arenaRepository.findAllByActiveIndex(true);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(allByActiveIndex)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }

    public ApiResponse addSlotToArena(UUID arenaId, UUID slotId) {
        Arena arena = arenaRepository.findById(arenaId).orElseThrow(()->{
            throw new ResourceNotFoundException(ARENA_NOT_FOUND+arenaId);
        });
        Slots slot = slotsRepository.findById(slotId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Slot not found with id: " + slotId);
        });
        if(arena.getSlots().contains(slot)){
            throw new BadRequestException("Slot with id: "+ slotId+" already exists in arena with id: "+arenaId);
        }
        arena.getSlots().add(slot);
        Arena save = arenaRepository.save(arena);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;
    }

    public ApiResponse getSlotsByArena(UUID arenaId) {
        Arena arena = arenaRepository.findByIdAndActiveIndex(arenaId,true);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(arena.getSlots())
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }
}
