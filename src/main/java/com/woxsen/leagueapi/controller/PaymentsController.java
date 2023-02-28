package com.woxsen.leagueapi.controller;

import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.woxsen.leagueapi.payload.request.PaymentRequest;
import com.woxsen.leagueapi.service.PaymentsService;
import com.woxsen.leagueapi.service.SlotsService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/payments")
@Slf4j
public class PaymentsController {

	@Autowired
	private SlotsService slotsService;
	@Autowired
	private PaymentsService paymentsService;
	private ModelMapper modelMapper =new ModelMapper();

	@PostMapping(value = "/users/{userId}/arenas/{arenaId}/slots/{slotId}/redirect",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = {
					MediaType.APPLICATION_ATOM_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public RedirectView addBooking(@PathVariable UUID userId, @PathVariable UUID arenaId, @PathVariable UUID slotId,
								   @RequestParam Map<String,String> formRequest) {
		PaymentRequest paymentRequest=modelMapper.map(formRequest,PaymentRequest.class);
		paymentsService.addPayment(paymentRequest, userId, arenaId, slotId);
		log.info(paymentRequest.toString());
		log.info(userId+"<>"+arenaId+"<>"+slotId+"<>"+formRequest.toString());
		RedirectView redirectView = new RedirectView();
		if (paymentRequest.getStatus().equals("success")) {
			redirectView.setUrl("http://localhost:3000/success");
		} else {
			redirectView.setUrl("http://localhost:3000/fail");
		}
//		redirectView.setUrl("http://localhost:3000/success");
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
