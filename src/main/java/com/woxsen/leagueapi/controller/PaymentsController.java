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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/payments")
public class PaymentsController {

    @Autowired
    private SlotsService slotsService;
    @Autowired
    private BookingsService bookingsService;

    @PostMapping("/redirect")
    public RedirectView addBooking(@PathVariable UUID userId, @Valid @RequestBody BookingRequest bookingRequest){

            RedirectView redirectView=new RedirectView();
            redirectView.setUrl("https://twitter.com");

            return redirectView;



    }
//    @GetMapping("/users/{userId}/bookings")
//    public ResponseEntity<ApiResponse> getBookingsByUser(@PathVariable UUID userId){
//        ApiResponse apiResponse = bookingsService.getBookingsByUser(userId);
//        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());
//
//    }
}
