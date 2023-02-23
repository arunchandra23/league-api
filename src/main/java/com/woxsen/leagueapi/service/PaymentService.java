package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
}
