package com.beauty.salon.model.repository;

import com.beauty.salon.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUsersByRole(String role);
    User findUserByUsername(String username);
}
