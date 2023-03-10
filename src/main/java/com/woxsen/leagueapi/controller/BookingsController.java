package com.woxsen.leagueapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BookingRequest;
import com.woxsen.leagueapi.service.BookingsService;
import com.woxsen.leagueapi.service.SlotsService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL)
public class BookingsController {

    @Autowired
    private SlotsService slotsService;
    @Autowired
    private BookingsService bookingsService;

    @PostMapping("/users/{userId}/bookings")
    public ResponseEntity<ApiResponse> addBooking(@PathVariable UUID userId,@RequestParam String day, @Valid @RequestBody BookingRequest bookingRequest){
        ApiResponse apiResponse = bookingsService.addBooking(userId,bookingRequest,day);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<ApiResponse> getBookingsByUser(@PathVariable UUID userId){
        ApiResponse apiResponse = bookingsService.getBookingsByUser(userId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<ApiResponse> getBookingDetailsByBookingId(@PathVariable UUID bookingId){
        ApiResponse apiResponse = bookingsService.getBookingDetailsByBookingId(bookingId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/arenas/{arenaId}/bookings")
    public ResponseEntity<ApiResponse> getAllBookingsByArena(@PathVariable UUID arenaId){
        ApiResponse apiResponse = bookingsService.getAllBookingsByArena(arenaId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
}
