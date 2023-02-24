package com.woxsen.leagueapi.controller;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BranchRequest;
import com.woxsen.leagueapi.payload.request.CourseRequest;
import com.woxsen.leagueapi.service.AuthenticationService;
import com.woxsen.leagueapi.service.BranchService;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/branches ")
public class CourseController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/{branchId}/courses")
    public ResponseEntity<ApiResponse> addCourse(@PathVariable UUID branchId, @Valid @RequestBody CourseRequest courseRequest){
        ApiResponse apiResponse = courseService.addCourse(branchId, courseRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/{branchId}/courses")
    public ResponseEntity<ApiResponse> getCoursesByBranch(@PathVariable UUID branchId){
        ApiResponse apiResponse = courseService.getCoursesByBranch(branchId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }



}
