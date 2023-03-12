package com.woxsen.leagueapi.payload.response;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingsAdminResponse {

    private UUID bookingId;
    private LocalDate bookingDate;
    private String arena;
    private String slot;
    private String paymentStatus;

    private String userEmail;
    private String userSchool;
    private String userCourse;
    private String userPhone;


}
