package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.repository.RoleRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
}
