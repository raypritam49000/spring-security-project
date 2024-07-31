//package com.security.rest.api.event;
//
//import com.security.rest.api.model.Privilege;
//import com.security.rest.api.model.Role;
//import com.security.rest.api.model.User;
//import com.security.rest.api.repository.PrivilegeRepository;
//import com.security.rest.api.repository.RoleRepository;
//import com.security.rest.api.repository.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.lang.Nullable;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Component
//public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//
//    boolean alreadySetup = false;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(@Nullable ContextRefreshedEvent event) {
//        if (alreadySetup) return;
//
//        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
//
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));
//
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
//        User user = new User();
//        user.setFirstName("Test");
//        user.setLastName("Test");
//        user.setPassword(passwordEncoder.encode("test"));
//        user.setEmail("test@test.com");
//        user.setRoles(Collections.singletonList(adminRole));
//        user.setEnabled(true);
//        userRepository.save(user);
//
//        alreadySetup = true;
//    }
//
//    @Transactional
//    public Privilege createPrivilegeIfNotFound(String name) {
//        Privilege privilege = privilegeRepository.findByName(name).get();
//        if (Objects.isNull(privilege)) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    public Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
//        Role role = roleRepository.findByName(name);
//        if (Objects.isNull(role)) {
//            role = new Role(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//}