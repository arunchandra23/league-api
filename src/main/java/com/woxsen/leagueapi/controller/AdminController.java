package com.woxsen.leagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.service.RoleService;
import com.woxsen.leagueapi.service.UserService;
import com.woxsen.leagueapi.utils.AppConstants;


@RestController
@RequestMapping(AppConstants.BASE_URL + "/admin")
public class AdminController {


    @Autowired
    private RoleService rolesService;
    @Autowired
    private UserService userService;

    @PutMapping("/{userName}/makeAdmin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity makeAdmin(@PathVariable String userName) {
        ApiResponse apiResponse=userService.makeAdmin(userName);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }




}
