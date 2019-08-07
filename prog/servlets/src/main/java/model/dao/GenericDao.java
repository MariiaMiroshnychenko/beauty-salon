package model.dao;

public interface GenericDao<T> extends AutoCloseable {
    void create(T t);
    void update(T t);
    void delete();

    void close();
}
