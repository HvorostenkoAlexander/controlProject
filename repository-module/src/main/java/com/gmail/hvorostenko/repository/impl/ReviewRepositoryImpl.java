package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.ReviewRepository;
import com.gmail.hvorostenko.repository.model.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.CONDITION_ZERO;
import static com.gmail.hvorostenko.repository.constant.ConstRepository.MAX_RESULTS_CONST;

@Repository
public class ReviewRepositoryImpl extends GenericRepositoryImpl<Long, Review> implements ReviewRepository {
    @Override
    public List<Review> findAll(Integer startPosition) {
        String queryString = "select r from Review as r";
        Query query = entityManager.createQuery(queryString);
        if (startPosition != CONDITION_ZERO) {
            int firstResult = (startPosition * MAX_RESULTS_CONST) - MAX_RESULTS_CONST;
            query.setFirstResult(firstResult);
        } else {
            query.setFirstResult(startPosition);
        }
        query.setMaxResults(MAX_RESULTS_CONST);
        List<Review> reviews = query.getResultList();
        return reviews;
    }

    @Override
    public int delete(List<String> idReview) {
        List<Long> id = idReview.stream().map(Long::parseLong).collect(Collectors.toList());
        String queryString = "delete from Review as r where r.id in :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
