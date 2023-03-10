package com.woxsen.leagueapi.payload.response;

import com.woxsen.leagueapi.entity.Payment;
import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UUID id;

    private String firstName;
    private String lastName;

    private String userName;

    private String email;

    private String phone;

    private String graduationYear;

    private CourseResponse course;

    private BranchResponse branch;


    private List<Role> roles;

    private Status status;
    private boolean activeIndex;
}
