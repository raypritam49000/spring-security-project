package com.security.rest.api.repository;

import com.security.rest.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);
}
