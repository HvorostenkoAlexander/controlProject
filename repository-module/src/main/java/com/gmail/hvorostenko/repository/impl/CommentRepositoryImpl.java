package com.gmail.hvorostenko.repository.impl;

import com.gmail.hvorostenko.repository.CommentRepository;
import com.gmail.hvorostenko.repository.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentRepositoryImpl extends GenericRepositoryImpl<Long, Comment> implements CommentRepository {

    @Override
    public List<Comment> findAllByArticle(Long id) {
        String queryString = "select c from Comment as c  join fetch c.user where c.article.id = :id order by c.date desc";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("id", id);
        List<Comment> comments = query.getResultList();
        return comments;
    }
}
