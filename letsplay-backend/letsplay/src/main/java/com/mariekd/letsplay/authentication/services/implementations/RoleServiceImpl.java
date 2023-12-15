package com.mariekd.letsplay.authentication.services.implementations;

import com.mariekd.letsplay.authentication.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.mariekd.letsplay.authentication.repositories.RoleRepository;
import com.mariekd.letsplay.authentication.entities.Role;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(String id, Role role) {
        if (roleRepository.existsById(Long.valueOf(id))) {
            role.setId(Long.valueOf(id));
            return roleRepository.save(role);
        } else {
            throw new RuntimeException("Role not found");
        }
    }
    @Override
    public void deleteRole(String id) {

    }
}
