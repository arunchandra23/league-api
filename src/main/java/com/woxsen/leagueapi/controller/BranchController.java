package com.woxsen.leagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BranchRequest;
import com.woxsen.leagueapi.service.AuthenticationService;
import com.woxsen.leagueapi.service.BranchService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/branches")
public class BranchController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<ApiResponse> addBranch(@Valid @RequestBody BranchRequest branchRequest){
        ApiResponse apiResponse = branchService.addBranch(branchRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllBranches(){
        ApiResponse apiResponse=branchService.getAllBranches();
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());
    }


}
