package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.ArticleRepository;
import com.gmail.hvorostenko.repository.model.Article;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.repository.constant.ConstRepository.CONDITION_ZERO;
import static com.gmail.hvorostenko.repository.constant.ConstRepository.MAX_RESULTS_CONST;

@Repository
public class ArticleRepositoryImpl extends GenericRepositoryImpl<Long, Article> implements ArticleRepository {
    @Override
    public List<Article> findAll(Integer startPosition) {
        String queryString = "select a from Article as a join fetch a.user order by a.date desc";
        Query query = entityManager.createQuery(queryString);
        if (startPosition != CONDITION_ZERO) {
            int firstResult = (startPosition * MAX_RESULTS_CONST) - MAX_RESULTS_CONST;
            query.setFirstResult(firstResult);
        } else {
            query.setFirstResult(startPosition);
        }
        query.setMaxResults(MAX_RESULTS_CONST);
        List<Article> articles = query.getResultList();
        return articles;
    }

    @Override
    public int delete(List<String> idArticles) {
        List<Long> id = idArticles.stream().map(Long::parseLong).collect(Collectors.toList());
        String queryString = "delete from Article as a where a.id in :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
