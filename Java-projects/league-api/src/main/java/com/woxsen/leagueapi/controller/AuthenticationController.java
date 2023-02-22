package com.woxsen.leagueapi.controller;

import com.woxsen.leagueapi.payload.request.LoginRequest;
import com.woxsen.leagueapi.payload.request.UserRequest;
import com.woxsen.leagueapi.payload.response.LoginResponse;
import com.woxsen.leagueapi.service.AuthenticationService;
import com.woxsen.leagueapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> signUp(@Valid @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(authenticationService.signUp(userRequest), HttpStatus.OK);

    }
}
