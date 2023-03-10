package com.woxsen.leagueapi.service;

import java.util.UUID;

import com.woxsen.leagueapi.utils.BookingStatus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.entity.Payment;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BookingRequest;
import com.woxsen.leagueapi.payload.request.PaymentRequest;
import com.woxsen.leagueapi.repository.BookingsRepository;
import com.woxsen.leagueapi.repository.PaymentRepository;
import com.woxsen.leagueapi.repository.UserRepository;

@Service
@Slf4j
public class PaymentsService {
	@Autowired
	private BookingsService bookingsService;
	@Autowired
	private UserRepository userRepository;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private BookingsRepository bookingsRepository;

	public Bookings addPayment(PaymentRequest paymentRequest, UUID userId, UUID arenaId, UUID slotId,String day) {
		User user = userRepository.findById(userId).get();
		Bookings booking = new Bookings();
		Payment payment = modelMapper.map(paymentRequest, Payment.class);
		payment.setUser(user);
		payment.setActiveIndex(true);
		if(paymentRequest.getStatus().equals("success")){
			BookingRequest bookingRequest = BookingRequest.builder().arenaId(arenaId).slotId(slotId).build();
			ApiResponse apiResponse = bookingsService.addBooking(userId, bookingRequest,day);
			booking = (Bookings) apiResponse.getData();
			Bookings save = bookingsRepository.findById(booking.getId()).get();
			save.setPayment(payment);
			Bookings save1 = bookingsRepository.save(save);
//			log.info(save1.getPayment().getBookings().getId().toString());
			return save1;
		}
		Payment save = paymentRepository.save(payment);

		return booking;

	}
}
