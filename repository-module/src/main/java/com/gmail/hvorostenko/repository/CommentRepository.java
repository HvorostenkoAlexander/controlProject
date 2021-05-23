package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.Comment;

import java.util.List;

public interface CommentRepository extends GenericRepository<Long, Comment> {
    List<Comment> findAllByArticle(Long id);
    int delete(List<String> idComments);
}
