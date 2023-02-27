package com.woxsen.leagueapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.woxsen.leagueapi.payload.request.PaymentRequest;
import com.woxsen.leagueapi.service.PaymentsService;
import com.woxsen.leagueapi.service.SlotsService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/payments")
public class PaymentsController {

	@Autowired
	private SlotsService slotsService;
	@Autowired
	private PaymentsService paymentsService;

	@PostMapping("/users/{userId}/arenas/{arenaId}/slots/{slotId}/redirect")
	public RedirectView addBooking(@PathVariable UUID userId, @PathVariable UUID arenaId, @PathVariable UUID slotId,
			@Valid @RequestBody PaymentRequest paymentRequest) {
		paymentsService.addPayment(paymentRequest, userId, arenaId, slotId);
		RedirectView redirectView = new RedirectView();
		if (paymentRequest.getStatus() == "success") {
			redirectView.setUrl("http://localhost:3000/success");
		} else {
			redirectView.setUrl("http://localhost:3000/fail");
		}

		return redirectView;

	}
	// @GetMapping("/users/{userId}/bookings")
	// public ResponseEntity<ApiResponse> getBookingsByUser(@PathVariable UUID
	// userId){
	// ApiResponse apiResponse = bookingsService.getBookingsByUser(userId);
	// return new ResponseEntity<>(apiResponse,apiResponse.getStatus());
	//
	// }
}
