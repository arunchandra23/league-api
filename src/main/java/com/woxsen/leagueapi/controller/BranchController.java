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

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL)
public class BranchController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BranchService branchService;

    @PostMapping("/branches")
    public ResponseEntity<ApiResponse> addBranch(@Valid @RequestBody BranchRequest branchRequest){
        ApiResponse apiResponse = branchService.addBranch(branchRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/schools/{schoolId}/branches")
    public ResponseEntity<ApiResponse> getAllBranchesBySchool(@PathVariable UUID schoolId){
        ApiResponse apiResponse=branchService.getAllBranchesBySchool(schoolId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());
    }


}
