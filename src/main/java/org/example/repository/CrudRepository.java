package org.example.repository;

import java.util.List;

public interface CrudRepository<T> {
    T getById(String id);

    List<T> getAll();

    boolean save(T entity);

    boolean saveAll(List<T> entity);

    boolean update(T entity);

    boolean delete(String id);
}
