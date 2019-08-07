package model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface MainMapper<T> {
    T extractFromResultSet(ResultSet resultSet) throws SQLException;
    T makeUnique(Map<Integer, T> cache, T entity);
}
