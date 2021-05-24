package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.Article;

import java.util.List;

public interface ArticleRepository extends GenericRepository<Long, Article>{
    List<Article> findAll(Integer pageCurrent);
    int delete(List<String> idArticles);
}
