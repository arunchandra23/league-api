package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.Course;
import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.request.LoginRequest;
import com.woxsen.leagueapi.payload.request.UserRequest;
import com.woxsen.leagueapi.payload.response.LoginResponse;
import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.repository.CourseRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.security.CustomUserDetailService;
import com.woxsen.leagueapi.security.JwtService;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.RoleName;
import com.woxsen.leagueapi.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
    
    private ModelMapper modelMapper=new ModelMapper();


    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        UserDetails user = customUserDetailService.loadUserByUsername(loginRequest.getEmail());
        String jwt = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwt)
                .build();
    }


    public LoginResponse signUp(UserRequest userRequest) {
        Course course=courseRepository.findById(userRequest.getCourseId()).orElseThrow(()-> {
            throw new ResourceNotFoundException("Course not found with Id: " + userRequest.getCourseId());
        });
        User user=modelMapper.map(userRequest,User.class);
        user.setCourse(course);
        user.setBranch(course.getBranch());
        user.setStatus(Status.ACTIVE);
        user.setActiveIndex(Boolean.TRUE);
        user.setRoles(Arrays.asList(new Role(RoleName.STUDENT)));
        User save = userRepository.save(user);
        UserDetails userD = customUserDetailService.loadUserByUsername(save.getEmail());
        String jwt = jwtService.generateToken(userD);
        return LoginResponse.builder()
                .token(jwt)
                .build();

    }
}
