package com.security.rest.api.service.impl;

import com.security.rest.api.dto.UserDTO;
import com.security.rest.api.dto.UserRequestDTO;
import com.security.rest.api.exception.ResourceConflictException;
import com.security.rest.api.mappers.UserMapper;
import com.security.rest.api.model.Privilege;
import com.security.rest.api.model.Role;
import com.security.rest.api.model.User;
import com.security.rest.api.repository.PrivilegeRepository;
import com.security.rest.api.repository.RoleRepository;
import com.security.rest.api.repository.UserRepository;
import com.security.rest.api.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserRequestDTO userRequestDTO) {
        // Check if the user already exists
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new ResourceConflictException("A user with this email already exists.");
        }

        // Create and populate the User entity
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setEmail(userRequestDTO.getEmail());
        user.setRoles(getRolesWithPrivileges(userRequestDTO.getRoles(), userRequestDTO.getPrivileges()));
        user.setEnabled(true);

        // Save and return the created user
        return UserMapper.INSTANCE.toDto(userRepository.save(user));
    }

    @Transactional
    private Collection<Role> getRolesWithPrivileges(Collection<String> roleNames, Collection<String> privilegeNames) {
        Collection<Privilege> privileges = privilegeNames.stream()
                .map(this::createPrivilegeIfNotFound)
                .collect(Collectors.toSet());

        return roleNames.stream()
                .map(roleName -> createRoleIfNotFound(roleName, privileges))
                .collect(Collectors.toSet());
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        return privilegeRepository.findByName(name).orElseGet(() -> privilegeRepository.save(new Privilege(name)));
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        return roleRepository.findByName(name)
                .map(role -> {
                    role.setPrivileges(privileges);
                    return role;
                })
                .orElseGet(() -> roleRepository.save(new Role(name, privileges)));
    }
}
