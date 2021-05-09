package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericRepositoryImpl<I, T> implements GenericRepository<I, T> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public GenericRepositoryImpl() {
        ParameterizedType genericClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericClass.getActualTypeArguments()[1];
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public T merge(T entity) {
        return entityManager.merge(entity);

    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        String queryString = "from " + entityClass.getName() + " c";
        Query query = entityManager.createQuery(queryString);
        return query.getResultList();

    }
}
