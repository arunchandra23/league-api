package com.woxsen.leagueapi.controller;

import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.LoginRequest;
import com.woxsen.leagueapi.payload.request.UserRequest;
import com.woxsen.leagueapi.payload.response.LoginResponse;
import com.woxsen.leagueapi.service.AuthenticationService;
import com.woxsen.leagueapi.service.UserService;
import com.woxsen.leagueapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/availability/email")
    public Boolean checkEmailAvailability(@RequestParam(required = false) String email) {
        if (email == null) {
            throw new BadRequestException("Mention the query parameter email");
        }
        return userService.isEmailAvailability(email);

    }
    @GetMapping("/availability/username")
    public Boolean checkUserNameAvailability(@RequestParam(required = false) String userName) {
        if (userName == null) {
            throw new BadRequestException("Mention the query parameter userName");
        }
        return userService.isUserNameAvailability(userName);
    }
}
