package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.ItemRepository;
import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.repository.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.CONDITION_ZERO;
import static com.gmail.hvorostenko.repository.constant.ConstRepository.MAX_RESULTS_CONST;

@Repository
public class ItemRepositoryImpl  extends GenericRepositoryImpl<Long, Item> implements ItemRepository {
    @Override
    public List<Item> findAll(Integer startPosition) {
        String queryString = "select i from Item as i order by i.name desc";
        Query query = entityManager.createQuery(queryString);
        if (startPosition != CONDITION_ZERO) {
            int firstResult = (startPosition * MAX_RESULTS_CONST) - MAX_RESULTS_CONST;
            query.setFirstResult(firstResult);
        } else {
            query.setFirstResult(startPosition);
        }
        query.setMaxResults(MAX_RESULTS_CONST);
        List<Item> items = query.getResultList();
        return items;
    }

    @Override
    public int delete(List<String> idItems) {
        List<Long> id = idItems.stream().map(Long::parseLong).collect(Collectors.toList());
        String queryString = "delete from Item as i where i.id in :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

}
