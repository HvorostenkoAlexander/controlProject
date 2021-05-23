package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.CommentRepository;
import com.gmail.hvorostenko.repository.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CommentRepositoryImpl extends GenericRepositoryImpl<Long, Comment> implements CommentRepository {

    @Override
    public List<Comment> findAllByArticle(Long id) {
        String queryString = "select c from Comment as c  join fetch c.user where c.article.id = :id order by c.date asc";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        List<Comment> comments = query.getResultList();
        return comments;
    }

    @Override
    public int delete(List<String> idComments) {
        List<Long> id = idComments.stream().map(Long::parseLong).collect(Collectors.toList());
        String queryString = "delete from Comment as c where c.id in :id";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
