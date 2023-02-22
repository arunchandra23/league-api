package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.repository.BranchRepository;
import com.woxsen.leagueapi.repository.SlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotsService {
    @Autowired
    private SlotsRepository slotsRepository;
}
