package com.woxsen.leagueapi.controller;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.service.RoleService;
import com.woxsen.leagueapi.service.UserService;
import com.woxsen.leagueapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
