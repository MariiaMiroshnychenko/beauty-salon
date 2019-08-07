package model.service.impl;

import model.dao.FactoryDao;
import model.dao.UserDao;
import model.entity.User;
import model.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void create(User user) {
        UserDao userDao = FactoryDao.getInstance().userDao();
        userDao.create(user);
        userDao.close();
    }

    @Override
    public User findUserByUsername(String username) {
        UserDao userDao = FactoryDao.getInstance().userDao();
        User user = userDao.findUserByUsername(username);
        userDao.close();
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        UserDao userDao = FactoryDao.getInstance().userDao();
        User user = userDao.findUserById(userId);
        userDao.close();
        return user;
    }

    @Override
    public List<User> findUsersByRole(String role) {
        UserDao userDao = FactoryDao.getInstance().userDao();
        List<User> users = userDao.findUsersByRole(role);
        userDao.close();
        return users;
    }
}
