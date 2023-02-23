package com.woxsen.leagueapi.service;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.exceptions.BadRequestException;
import com.woxsen.leagueapi.repository.RoleRepository;
import com.woxsen.leagueapi.repository.UserRepository;
import com.woxsen.leagueapi.utils.RoleName;
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

        if (roleRepository.count() == 3) throw new BadRequestException("Roles added already!");
//        Role student = Role.builder()
//                .name(RoleName.STUDENT)
//                .build();
//        Role admin = Role.builder()
//                .name(RoleName.ADMIN)
//                .build();
//        Role dba = Role.builder()
//                .name(RoleName.DBA)
//                .build();
        List<Role> roles = new ArrayList<>(Arrays.asList(new Role(RoleName.STUDENT),new Role(RoleName.DBA),new Role(RoleName.ADMIN),new Role(RoleName.FACULTY),new Role(RoleName.STAFF)));

        List<Role> roles1 = roleRepository.saveAll(roles);

        return roles1;

    }
}
