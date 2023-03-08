package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SlotRequest;
import com.woxsen.leagueapi.repository.SlotsRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.Status;

@Service
public class SlotsService {
    @Autowired
    private SlotsRepository slotsRepository;

    public ApiResponse addSlot(SlotRequest slotRequest) {
        Slots slot=Slots.builder()
                .slot(slotRequest.getSlot())
                .startTime(slotRequest.getStartTime())
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Slots save = slotsRepository.save(slot);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;
    }

    public ApiResponse getAllSlots() {
        List<Slots> allByActiveIndex = slotsRepository.findAllByActiveIndex(true);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(allByActiveIndex)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }
}
