package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.OrderRepository;
import com.gmail.hvorostenko.repository.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.CONDITION_ZERO;
import static com.gmail.hvorostenko.repository.constant.ConstRepository.MAX_RESULTS_CONST;

@Repository
public class OrderRepositoryImpl extends GenericRepositoryImpl<Long, Order> implements OrderRepository {

    @Override
    public List<Order> findAll(Integer startPosition) {
        String queryString = "select o from Order as o join fetch o.user order by o.date desc";
        Query query = entityManager.createQuery(queryString);
        if (startPosition != CONDITION_ZERO) {
            int firstResult = (startPosition * MAX_RESULTS_CONST) - MAX_RESULTS_CONST;
            query.setFirstResult(firstResult);
        } else {
            query.setFirstResult(startPosition);
        }
        query.setMaxResults(MAX_RESULTS_CONST);
        List<Order> orders = query.getResultList();
        return orders;
    }

    @Override
    public List<Order> findAll(Integer startPosition, String nameUser) {
        String queryString = "select o from Order as o join fetch o.user where o.user.email=:nameUser order by o.date desc";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("nameUser", nameUser);
        if (startPosition != CONDITION_ZERO) {
            int firstResult = (startPosition * MAX_RESULTS_CONST) - MAX_RESULTS_CONST;
            query.setFirstResult(firstResult);
        } else {
            query.setFirstResult(startPosition);
        }
        query.setMaxResults(MAX_RESULTS_CONST);
        List<Order> orders = query.getResultList();
        return orders;
    }
}
