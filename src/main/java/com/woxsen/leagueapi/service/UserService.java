package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.utils.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Modifying
    public ApiResponse makeAdmin(String userName) {
        User user = userRepository.findByUserName(userName);
        if(user==null) throw new ResourceNotFoundException("User not found with username: "+userName);
        user.getRoles().add(new Role(RoleName.ADMIN));
        userRepository.save(user);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .errors(new ArrayList<>())
                .message("Made user with username: " + userName + " ADMIN")
                .success(Boolean.TRUE)
                .data(new ArrayList<>())
                .build();
        return apiResponse;

    }
    public Boolean isEmailAvailability(String email) {
        User byEmail = userRepository.findByEmail(email);
        return byEmail==null?Boolean.TRUE:Boolean.FALSE;
    }

    public Boolean isUserNameAvailability(String userName) {
        User byUserName = userRepository.findByUserName(userName);
        return byUserName==null?Boolean.TRUE:Boolean.FALSE;
    }
}
