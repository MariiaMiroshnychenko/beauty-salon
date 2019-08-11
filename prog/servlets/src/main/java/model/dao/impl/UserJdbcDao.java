package model.dao.impl;

import model.dao.UserDao;
import model.entity.User;
import model.mapper.impl.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserJdbcDao implements UserDao {
    private UserMapper userMapper = new UserMapper();
    private Map<Integer, User> userMap = new HashMap<>();
    private Connection connection;

    public UserJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement statement = connection.prepareStatement("insert into `user_table` (name, surname, email, role, username, password) values (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getUsername());
            statement.setString(6, user.getPassword());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete() {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByUsername(String username) {
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement("select * from `user_table` where username=?")) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(user)) {
                userMapper.makeUnique(userMap, user);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement("select * from `user_table` where id=?")) {
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
            }
            if (Objects.nonNull(user)) {
                userMapper.makeUnique(userMap, user);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findUsersByRole(String role) {
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("select * from `user_table` where role=?")) {
            statement.setString(1, role);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User schedule = userMapper.extractFromResultSet(resultSet);

                userMapper.makeUnique(userMap, schedule);
            }
            resultSet.close();
            users = new ArrayList<>(userMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
