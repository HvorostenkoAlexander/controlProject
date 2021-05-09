package com.gmail.hvorostenko.repository;

import java.util.List;

public interface GenericRepository<I, T> {
    void persist(T entity);
    T merge(T entity);
    void remove(T entity);
    T findById(I id);
    List<T> findAll();
}
