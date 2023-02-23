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
@RequestMapping(AppConstants.BASE_URL)
public class CourseController {

    @Autowired
    private BranchService branchService;
    @Autowired
    private CourseService courseService;

    @PostMapping("branch/{branchId}/course")
    public ResponseEntity<ApiResponse> addCourse(@PathVariable UUID branchId, @Valid @RequestBody CourseRequest courseRequest){
        ApiResponse apiResponse = courseService.addCourse(branchId, courseRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }


}
