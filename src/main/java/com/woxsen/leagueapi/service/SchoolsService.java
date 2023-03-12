package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Schools;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.SchoolRequest;
import com.woxsen.leagueapi.payload.response.SchoolResponse;
import com.woxsen.leagueapi.repository.SchoolsRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.Status;

@Service
public class SchoolsService {
    @Autowired
    private SchoolsRepository schoolsRepository;

    ModelMapper modelMapper =new ModelMapper();

    public ApiResponse addSchool(SchoolRequest schoolRequest) {
        Schools school=Schools.builder()
                .status(Status.ACTIVE)
                .name(schoolRequest.getName())
                .activeIndex(Boolean.TRUE)
                .build();
        Schools save = schoolsRepository.save(school);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;
    }

    public ApiResponse getAllSchools() {
        List<Schools> schools= schoolsRepository.findAllByActiveIndex(true);
        List<SchoolResponse> SchoolsList = modelMapper.map(schools, new TypeToken<List<SchoolResponse>>(){}.getType());
        ApiResponse apiResponse= ApiResponse.builder()
                .data(SchoolsList)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }
}
