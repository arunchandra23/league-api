package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.Arrays;

import com.woxsen.leagueapi.utils.GenderTypes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Course;
import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.exceptions.UnauthorizedException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.LoginRequest;
import com.woxsen.leagueapi.payload.request.UserRequest;
import com.woxsen.leagueapi.payload.response.LoginResponse;
import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.repository.CourseRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.security.CustomUserDetailService;
import com.woxsen.leagueapi.security.JwtService;
import com.woxsen.leagueapi.security.UserPrinciple;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.RoleName;
import com.woxsen.leagueapi.utils.Status;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BranchRepository branchRepository;
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
        User user = modelMapper.map(userRequest, User.class);
        user.setGender(GenderTypes.valueOf(userRequest.getGender()));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setCourse(course);
        user.setBranch(course.getBranch());
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


}
