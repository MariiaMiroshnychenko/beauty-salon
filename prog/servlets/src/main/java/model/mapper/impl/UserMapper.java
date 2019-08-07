package model.mapper.impl;

import model.entity.User;
import model.mapper.MainMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements MainMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String role = resultSet.getString("role");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        return new User(id, name, surname, email, role, username, password);
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
