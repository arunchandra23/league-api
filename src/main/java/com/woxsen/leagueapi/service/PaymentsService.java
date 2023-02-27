package com.woxsen.leagueapi.service;

import java.util.UUID;

import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.entity.Payment;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BookingRequest;
import com.woxsen.leagueapi.payload.request.PaymentRequest;
import com.woxsen.leagueapi.repository.PaymentRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService {
    @Autowired
    private BookingsService bookingsService;
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper =new ModelMapper();
    @Autowired
    private PaymentRepository paymentRepository;


    public Payment addPayment(PaymentRequest paymentRequest, UUID userId, UUID arenaId, UUID slotId) {
        User user =userRepository.findById(userId).get();
        Bookings booking=null;
        if(paymentRequest.getStatus()=="success"){
            BookingRequest bookingRequest=BookingRequest.builder()
                    .arenaId(arenaId)
                    .slotId(slotId)
                    .build();
            ApiResponse apiResponse = bookingsService.addBooking(userId, bookingRequest);
            booking= (Bookings) apiResponse.getData();

        }
        Payment payment =modelMapper.map(paymentRequest,Payment.class);
        payment.setUser(user);
        payment.setBookings(booking);
        Payment save = paymentRepository.save(payment);
        return save;

    }
}
