package com.beauty.salon.model.service.impl;

import com.beauty.salon.model.entity.User;
import com.beauty.salon.model.repository.UserRepository;
import com.beauty.salon.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.getOne(userId);
    }

    @Override
    public List<User> findUsersByRole(String role) {
        return userRepository.findUsersByRole(role);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s);
    }
}
