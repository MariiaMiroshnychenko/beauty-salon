package com.beauty.salon.model.service;

import com.beauty.salon.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void create(User user);
    User findUserById(Integer userId);
    List<User> findUsersByRole(String role);
}
