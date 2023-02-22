package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.Branch;
import com.woxsen.leagueapi.entity.Course;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.CourseRequest;
import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.repository.CourseRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.CourseTypes;
import com.woxsen.leagueapi.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BranchRepository branchRepository;

    public ApiResponse addCourse(UUID branchId, CourseRequest courseRequest) {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Not found branch with Id: " + branchId);
        });
        Course course=Course.builder()
                .name(courseRequest.getName())
                .graduationYear(courseRequest.getGraduationYear())
                .type(CourseTypes.valueOf(courseRequest.getCourseType()))
                .branch(branch)
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Course save = courseRepository.save(course);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;

    }
}
