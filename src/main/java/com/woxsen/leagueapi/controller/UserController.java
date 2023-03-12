package com.woxsen.leagueapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.service.UserService;
import com.woxsen.leagueapi.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{userId}/profile")
    public ResponseEntity<ApiResponse> getUserProfile(@PathVariable UUID userId){
        ApiResponse apiResponse=userService.getUserProfile(userId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }

}
