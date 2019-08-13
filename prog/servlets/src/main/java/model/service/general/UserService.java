package model.service.general;

import model.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);
    User findUserByUsername(String username);
    User findUserById(Integer userId);
    List<User> findUsersByRole(String role);
}
