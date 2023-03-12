package com.woxsen.leagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.LoginRequest;
import com.woxsen.leagueapi.payload.request.PasswordResetRequest;
import com.woxsen.leagueapi.payload.request.UserRequest;
import com.woxsen.leagueapi.service.AuthenticationService;
import com.woxsen.leagueapi.service.UserService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(authenticationService.signUp(userRequest), HttpStatus.OK);

    }
    @PostMapping("/password/reset")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest){
        ApiResponse apiResponse = authenticationService.resetPassword(passwordResetRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());

    }
    @GetMapping("/security-question")
    public ResponseEntity<ApiResponse> getSecurityQuestionByUser(@RequestParam String email){
        ApiResponse apiResponse = authenticationService.getSecurityQuestionByUser(email);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }

}
