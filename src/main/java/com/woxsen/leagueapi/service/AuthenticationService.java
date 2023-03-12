package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Course;
import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.entity.SecurityQuestions;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.exceptions.UnauthorizedException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.LoginRequest;
import com.woxsen.leagueapi.payload.request.PasswordResetRequest;
import com.woxsen.leagueapi.payload.request.UserRequest;
import com.woxsen.leagueapi.payload.response.LoginResponse;
import com.woxsen.leagueapi.repository.CourseRepository;
import com.woxsen.leagueapi.repository.SecurityQuestionsRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.security.CustomUserDetailService;
import com.woxsen.leagueapi.security.JwtService;
import com.woxsen.leagueapi.security.UserPrinciple;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.GenderTypes;
import com.woxsen.leagueapi.utils.RoleName;
import com.woxsen.leagueapi.utils.Status;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private SecurityQuestionsRepository securityQuestionsRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();


    public ApiResponse login(LoginRequest loginRequest) {
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        }catch (Exception e){
            throw new UnauthorizedException(e.getMessage());
        }
        UserPrinciple user = (UserPrinciple)customUserDetailService.loadUserByUsername(loginRequest.getEmail());
        String jwt = jwtService.generateToken(user);
        LoginResponse token = LoginResponse.builder()
                .token(jwt)
                .build();
        return ApiResponse.builder()
                .success(Boolean.TRUE)
                .errors(new ArrayList<>())
                .message(AppConstants.LOGIN_SUCCESS)
                .data(token)
                .build();
    }


    public ApiResponse signUp(UserRequest userRequest) {
        Course course = courseRepository.findById(userRequest.getCourseId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Course not found with Id: " + userRequest.getCourseId());
        });
        SecurityQuestions securityQuestion = securityQuestionsRepository.findByIdAndActiveIndex(userRequest.getSecurityQuestionId(), true);
        User user = modelMapper.map(userRequest, User.class);
        user.setQuestion(securityQuestion);
        user.setSecurityAnswer(userRequest.getSecurityAnswer().toLowerCase());
        user.setGender(GenderTypes.valueOf(userRequest.getGender()));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setCourse(course);
        user.setSchool(course.getSchool());
        user.setStatus(Status.ACTIVE);
        user.setActiveIndex(Boolean.TRUE);
        user.setRoles(Arrays.asList(new Role(RoleName.STUDENT)));
        User save = userRepository.save(user);
        UserPrinciple userD = (UserPrinciple)customUserDetailService.loadUserByUsername(save.getEmail());
        String jwt = jwtService.generateToken(userD);
        LoginResponse token = LoginResponse.builder()
                .token(jwt)
                .build();
        return ApiResponse.builder()
                .success(Boolean.TRUE)
                .errors(new ArrayList<>())
                .message(AppConstants.SIGNUP_SUCCESS)
                .data(token)
                .build();

    }


    public ApiResponse getSecurityQuestionByUser(String  email) {
//        User user=userRepository.findByIdAndActiveIndex(userId,true);
        User user=userRepository.findByEmailAndActiveIndex(email, true);
        if (user==null){
            throw new ResourceNotFoundException("User not found with email: "+email);
        }
        ApiResponse apiResponse = ApiResponse.builder().data(user.getQuestion()).errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS).success(Boolean.TRUE).status(HttpStatus.OK).build();
        return apiResponse;
    }

    public ApiResponse resetPassword(PasswordResetRequest passwordResetRequest) {
        User user=userRepository.findByEmailAndActiveIndex(passwordResetRequest.getEmail(), true);
        if (user==null){
            throw new ResourceNotFoundException("User not found with email: "+passwordResetRequest.getEmail());
        } 
        if(user.getQuestion().getId().equals(passwordResetRequest.getSecurityQuestionId())){
            if(user.getSecurityAnswer().equals(passwordResetRequest.getSecurityAnswer().toLowerCase())){
                user.setPassword(passwordEncoder.encode(passwordResetRequest.getNewPassword()));
                userRepository.save(user);
                return ApiResponse.builder()
                        .data(new ArrayList<>())
                        .status(HttpStatus.OK)
                        .errors(new ArrayList<>())
                        .message(AppConstants.PASSWORD_RESET_SUCCESS)
                        .build();
            }
            throw new BadRequestException("Wrong security answer!");
        }else{
            throw new BadRequestException("Question id provided do not match the user's question id");
        }

    }
}
