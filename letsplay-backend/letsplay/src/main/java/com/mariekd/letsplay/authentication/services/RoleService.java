package com.mariekd.letsplay.authentication.services;

import com.mariekd.letsplay.authentication.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(String id);
    Role createRole(Role role);
    Role updateRole(String id, Role role);
    void deleteRole(String id);
}
