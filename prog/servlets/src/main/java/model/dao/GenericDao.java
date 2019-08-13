package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public interface GenericDao<T> extends AutoCloseable {
    void create(T t);

    void close();
}
