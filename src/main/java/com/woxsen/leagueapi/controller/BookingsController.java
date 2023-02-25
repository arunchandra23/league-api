package com.woxsen.leagueapi.controller;

import com.woxsen.leagueapi.payload.request.BookingRequest;
import com.woxsen.leagueapi.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SlotRequest;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.service.SlotsService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL)
public class BookingsController {

    @Autowired
    private SlotsService slotsService;
    @Autowired
    private BookingsService bookingsService;

    @PostMapping("/users/{userId}/bookings")
    public ResponseEntity<ApiResponse> addBooking(@PathVariable UUID userId, @Valid @RequestBody BookingRequest bookingRequest){
        ApiResponse apiResponse = bookingsService.addBooking(userId,bookingRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<ApiResponse> getAllSlots(){
        ApiResponse apiResponse = slotsService.getAllSlots();
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
}
