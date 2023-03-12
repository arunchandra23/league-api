package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.response.UserProfile;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.RoleName;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper=new ModelMapper();

    @Modifying
    public ApiResponse makeAdmin(String userName) {
        User user = userRepository.findByUserName(userName);
        if(user==null) throw new ResourceNotFoundException("User not found with username: "+userName);
        if(user.getRoles().toString().contains(RoleName.ADMIN.toString())){
            throw new BadRequestException("User with username: "+userName+" is already admin");
        }
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

    public ApiResponse getUserProfile(UUID userId) {
        User user = userRepository.findByIdAndActiveIndex(userId, true);
        UserProfile userProfile =modelMapper.map(user,UserProfile.class);
//        log.info(userProfile.toString());
        ApiResponse apiResponse= ApiResponse.builder()
                .data(userProfile)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;

    }
}
