package com.woxsen.leagueapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woxsen.leagueapi.entity.Role;
import com.woxsen.leagueapi.service.RoleService;
import com.woxsen.leagueapi.utils.AppConstants;


@RestController
@RequestMapping(AppConstants.BASE_URL + "/roles")
public class RolesController {


    @Autowired
    private RoleService rolesService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Role>> addRoles() {
        List<Role> roles = rolesService.addRoles();
        return new ResponseEntity<>(roles, HttpStatus.CREATED);
    }




}
