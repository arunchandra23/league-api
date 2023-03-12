package com.woxsen.leagueapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SecurityQuestionRequest;
import com.woxsen.leagueapi.service.CourseService;
import com.woxsen.leagueapi.service.SecurityQuestionsService;
import com.woxsen.leagueapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstants.BASE_URL+"/security/questions")
public class SecurityQuestionsController {

    @Autowired
    private SecurityQuestionsService securityQuestionsService;
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse> addQuestion(@Valid @RequestBody SecurityQuestionRequest securityQuestionRequest){
        ApiResponse apiResponse = securityQuestionsService.addQuestion(securityQuestionRequest);
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllQuestions(){
        ApiResponse apiResponse = securityQuestionsService.getAllQuestions();
        return new ResponseEntity<>(apiResponse,apiResponse.getStatus());

    }
}
