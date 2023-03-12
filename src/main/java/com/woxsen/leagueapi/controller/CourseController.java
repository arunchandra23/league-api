package com.woxsen.leagueapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.CourseRequest;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/schools")
public class CourseController {

//    @Autowired
//    private BranchService branchService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/{schoolId}/courses")
    public ResponseEntity<ApiResponse> addCourse(@PathVariable UUID schoolId, @Valid @RequestBody CourseRequest courseRequest){
        ApiResponse apiResponse = courseService.addCourse(schoolId, courseRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping("/{schoolId}/courses")
    public ResponseEntity<ApiResponse> getCoursesBySchool(@PathVariable UUID schoolId){
        ApiResponse apiResponse = courseService.getCoursesBySchool(schoolId);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
}
