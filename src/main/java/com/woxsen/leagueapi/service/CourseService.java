package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Course;
import com.woxsen.leagueapi.entity.Schools;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.CourseRequest;
import com.woxsen.leagueapi.repository.CourseRepository;
import com.woxsen.leagueapi.repository.SchoolsRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.CourseTypes;
import com.woxsen.leagueapi.utils.Status;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SchoolsRepository schoolsRepository;

    public ApiResponse addCourse(UUID schoolId, CourseRequest courseRequest) {
        Schools school = schoolsRepository.findByIdAndActiveIndex(schoolId, true);
        if(school==null){
            throw new ResourceNotFoundException("Not found branch with Id: " + schoolId);
        };
        Course course=Course.builder()
                .name(courseRequest.getName())
                .type(CourseTypes.valueOf(courseRequest.getCourseType()))
                .school(school)
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

    public ApiResponse getCoursesBySchool(UUID getCoursesBySchool) {
        List<Course> courses = courseRepository.findBySchool_id(getCoursesBySchool);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(courses)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }
}
