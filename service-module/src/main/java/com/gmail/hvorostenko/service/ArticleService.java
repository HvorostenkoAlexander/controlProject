package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.ArticleDTO;

import java.util.List;

public interface ArticleService {
    List<ArticleDTO> findAllByPage(Integer pageCurrent);
    ArticleDTO findById(String id);
    List<ArticleDTO> findAll();
    void delete(Long id);
    void add(Long id, ArticleDTO article);
}
