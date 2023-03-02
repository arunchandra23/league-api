package com.woxsen.leagueapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.response.BookingDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Arena;
import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.entity.Slots;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BookingRequest;
import com.woxsen.leagueapi.payload.response.SlotsResponse;
import com.woxsen.leagueapi.repository.*;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.BookingStatus;
import com.woxsen.leagueapi.utils.Status;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingsService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private ArenaRepository arenaRepository;
    @Autowired
    private SlotsRepository slotsRepository;
    @Autowired
    private UserRepository userRepository;

    public static LocalDate getDate(String day){
        if(day.equals("today")){
            return LocalDate.now();
        }if(day.equals("tomorrow")){
            return LocalDate.now().plusDays(1);
        }
        else{
            return LocalDate.now().plusDays(2);
        }
    };
    public ApiResponse addBooking(UUID userId, BookingRequest bookingRequest,String day) {
//        List<Bookings> unAvailableSlots = bookingsRepository.getUnAvailableSlots(bookingRequest.getArenaId(), LocalDate.now());

        if(!slotsRepository.existsByIdAndActiveIndex(bookingRequest.getSlotId(),true)){
            throw new BadRequestException("Slot with id: "+bookingRequest.getSlotId()+" does not exist");
        }
        List<SlotsResponse> availableSlots = ArenaService.getAvailableSlots(bookingRequest.getArenaId(), arenaRepository, bookingsRepository,getDate(day));
        availableSlots.stream().forEach(slot->{
            if(slot.getId().toString().equals(bookingRequest.getSlotId().toString())){
                if(!slot.isAvailable()){
                    throw new BadRequestException("Slot with id: "+bookingRequest.getSlotId()+" is not available");
                }
            }
        });
        Arena arena = arenaRepository.findByIdAndActiveIndex(bookingRequest.getArenaId(),true);
        Slots slot = slotsRepository.findByIdAndActiveIndex(bookingRequest.getSlotId(), true);
        User user = userRepository.findByIdAndActiveIndex(userId, true);
        Bookings booking=Bookings.builder()
                .arena(arena)
                .slot(slot)
                .user(user)
                .bookingStatus(BookingStatus.PENDING)
                .date(getDate(day))
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Bookings save = bookingsRepository.save(booking);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;
    }

    public ApiResponse getAllBookings(){
        List<Bookings> allByActiveIndex = bookingsRepository.findAllByActiveIndex(true);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(allByActiveIndex)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }
    public ApiResponse getBookingsByUser(UUID userId){
        List<Bookings> bookingsByUser = bookingsRepository.getBookingsByUser(userId);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(bookingsByUser)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }

    public ApiResponse getBookingDetailsByBookingId(UUID bookingId) {
        Bookings booking = bookingsRepository.findByIdAndActiveIndex(bookingId, true);
        if(booking==null){
            throw new ResourceNotFoundException("Booking not found with id: "+bookingId);
        }
        BookingDetailsResponse response=BookingDetailsResponse.builder()
                .bookingId(booking.getId())
                .bookingDate(booking.getDate().toString())
                .paymentStatus(booking.getPayment().getStatus())
                .arena(booking.getArena().getName())
                .build();
        ApiResponse apiResponse= ApiResponse.builder()
                .data(response)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;

    }
}
