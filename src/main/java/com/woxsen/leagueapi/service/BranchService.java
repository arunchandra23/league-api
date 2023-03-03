package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.woxsen.leagueapi.entity.Branch;
import com.woxsen.leagueapi.payload.ApiResponse;
import com.woxsen.leagueapi.payload.request.BranchRequest;
import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.utils.AppConstants;
import com.woxsen.leagueapi.utils.Status;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    public ApiResponse addBranch(BranchRequest branchRequest) {
        Branch branch=Branch.builder()
                .name(branchRequest.getName())
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Branch save = branchRepository.save(branch);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(save)
                .errors(new ArrayList<>())
                .message(AppConstants.INSERT_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.CREATED)
                .build();
        return apiResponse;
    }

    public ApiResponse getAllBranches() {
        List<Branch> branches = branchRepository.findAllByActiveIndex(true);
        ApiResponse apiResponse= ApiResponse.builder()
                .data(branches)
                .errors(new ArrayList<>())
                .message(AppConstants.RETRIEVAL_SUCCESS)
                .success(Boolean.TRUE)
                .status(HttpStatus.OK)
                .build();
        return apiResponse;
    }
}
