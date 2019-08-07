package model.dao;

import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findUserByUsername(String username);
    User findUserById(Integer userId);
    List<User> findUsersByRole(String role);
}
