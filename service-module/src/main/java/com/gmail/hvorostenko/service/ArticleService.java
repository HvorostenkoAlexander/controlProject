package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.PageDTO;

import java.util.List;

public interface ArticleService {
    PageDTO<ArticleDTO> findAllSortDate(Integer pageCurrent);
    ArticleDTO findById(String id);
    List<ArticleDTO> findAll();
    void delete(Long id);
    void add(Long id, ArticleDTO article);
    Integer deleteArticles(List<String> idArticles);
    void update(ArticleDTO articleDTO, String idArticle);
}
