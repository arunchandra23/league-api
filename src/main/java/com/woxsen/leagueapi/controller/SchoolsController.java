package com.woxsen.leagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SchoolRequest;
import com.woxsen.leagueapi.service.SchoolsService;
import com.woxsen.leagueapi.utils.AppConstants;


@RestController
@RequestMapping(AppConstants.BASE_URL + "/Schools")
public class SchoolsController {


    @Autowired
    private SchoolsService schoolsService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> addSchool(@RequestBody SchoolRequest schoolRequest) {
        ApiResponse apiResponse = schoolsService.addSchool(schoolRequest);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getAllSchools() {
        ApiResponse apiResponse = schoolsService.getAllSchools();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }




}
