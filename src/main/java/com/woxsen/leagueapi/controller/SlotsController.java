package com.woxsen.leagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SlotRequest;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.service.SlotsService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/slots")
public class SlotsController {

    @Autowired
    private SlotsService slotsService;
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse> addSlot(@Valid @RequestBody SlotRequest slotRequest){
        ApiResponse apiResponse = slotsService.addSlot(slotRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllSlots(){
        ApiResponse apiResponse = slotsService.getAllSlots();
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
}
