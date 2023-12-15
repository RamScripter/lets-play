package com.mariekd.letsplay.authentication.controller;

import com.mariekd.letsplay.authentication.entities.Role;
import com.mariekd.letsplay.controllers.UserController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.mariekd.letsplay.authentication.services.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class RoleController {

    @Autowired
    private final RoleService roleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @GetMapping
    public List<Role> getAllRoles() {
        LOGGER.info("Getting all roles: " + roleService.getAllRoles());
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable String id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable String id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
    }




}
