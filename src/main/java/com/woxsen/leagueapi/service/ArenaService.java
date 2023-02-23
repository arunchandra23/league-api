package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.repository.ArenaRepository;
import com.woxsen.leagueapi.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArenaService {
    @Autowired
    private ArenaRepository arenaRepository;
}
