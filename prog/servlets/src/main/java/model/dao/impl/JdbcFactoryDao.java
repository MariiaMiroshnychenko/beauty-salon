package model.dao.impl;

import model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcFactoryDao extends FactoryDao {
    private DataSource dataSource = ConnectionPool.getDataSource();

    @Override
    public LanguageDao languageDao() {
        return new LanguageJdbcDao(getConnection());
    }

    @Override
    public ProcedureDao procedureDao() {
        return new ProcedureJdbcDao(getConnection());
    }

    @Override
    public RecordDao recordDao() {
        return new RecordJdbcDao(getConnection());
    }

    @Override
    public FeedbackDao reviewDao() {
        return new FeedbackJdbcDao(getConnection());
    }

    @Override
    public ScheduleDao scheduleDao() {
        return new ScheduleJdbcDao(getConnection());
    }

    @Override
    public UserDao userDao() {
        return new UserJdbcDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
