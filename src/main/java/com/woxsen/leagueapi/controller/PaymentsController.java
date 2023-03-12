package com.woxsen.leagueapi.controller;

import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.payload.request.PaymentRequest;
import com.woxsen.leagueapi.service.PaymentsService;
import com.woxsen.leagueapi.service.SlotsService;
import com.woxsen.leagueapi.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

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
	public RedirectView addBooking(@PathVariable UUID userId, @PathVariable UUID arenaId, @PathVariable UUID slotId,@RequestParam String day,
								   @RequestParam Map<String,String> formRequest) {
		PaymentRequest paymentRequest=modelMapper.map(formRequest,PaymentRequest.class);
		Bookings booking = paymentsService.addPayment(paymentRequest, userId, arenaId, slotId,day);
		log.info(paymentRequest.toString());
		log.info(userId+"<>"+arenaId+"<>"+slotId+"<>"+formRequest.toString());
		RedirectView redirectView = new RedirectView();
		if (paymentRequest.getStatus().equals("success")) {
			redirectView.setUrl("http://localhost:3000/success?bookingId="+booking.getId());
		} else {
			redirectView.setUrl("http://localhost:3000/fail");
		}
//		redirectView.setUrl("http://localhost:3000/success");
		return redirectView;

	}
	@PostMapping(value = "/bookings/{bookingId}/extension/redirect",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = {
					MediaType.APPLICATION_ATOM_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public RedirectView addExtensionForBooking(@PathVariable UUID bookingId,
								   @RequestParam Map<String,String> formRequest) {
		PaymentRequest paymentRequest=modelMapper.map(formRequest,PaymentRequest.class);
		Bookings booking = paymentsService.addExtensionPayment(paymentRequest,bookingId);
		log.info(paymentRequest.toString());
		RedirectView redirectView = new RedirectView();
		if (paymentRequest.getStatus().equals("success")) {
			redirectView.setUrl("http://localhost:3000/success?bookingId="+booking.getId());
		} else {
			redirectView.setUrl("http://localhost:3000/fail");
		}
//		redirectView.setUrl("http://localhost:3000/success");
		return redirectView;

	}

}
