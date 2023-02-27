package com.woxsen.leagueapi.service;

import java.util.ArrayList;
import java.util.List;

import com.woxsen.leagueapi.payload.request.PaymentRequest;
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
public class PaymentsService {
    @Autowired
    private BranchRepository branchRepository;


    public void addPayment(PaymentRequest paymentRequest) {

    }
}
