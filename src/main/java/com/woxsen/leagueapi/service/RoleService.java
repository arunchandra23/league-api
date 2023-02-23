package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.repository.RoleRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.utils.RoleName;
import com.woxsen.leagueapi.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> addRoles() {

        if (roleRepository.count() >= 3) throw new BadRequestException("Roles added already!");
        Role student = Role.builder()
                .name(RoleName.STUDENT)
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Role admin = Role.builder()
                .name(RoleName.ADMIN)
                .build();
        Role dba = Role.builder()
                .name(RoleName.DBA)
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Role faculty = Role.builder()
                .name(RoleName.FACULTY)
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        Role staff = Role.builder()
                .name(RoleName.STAFF)
                .activeIndex(Boolean.TRUE)
                .status(Status.ACTIVE)
                .build();
        List<Role> roles = new ArrayList<>(Arrays.asList(staff,student,dba,faculty,admin));

        List<Role> roles1 = roleRepository.saveAll(roles);

        return roles1;

    }
}
