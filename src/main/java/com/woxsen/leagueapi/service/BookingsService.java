package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.repository.BookingsRepository;
import com.woxsen.leagueapi.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingsService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BookingsRepository bookingsRepository;
}
