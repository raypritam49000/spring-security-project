package com.security.rest.api.repository;

import com.security.rest.api.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, String> {
    Optional<Privilege> findByName(String name);
}
