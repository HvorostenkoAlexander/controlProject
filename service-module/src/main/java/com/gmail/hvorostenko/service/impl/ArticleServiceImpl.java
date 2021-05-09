package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.ArticleRepository;
import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.converter.ArticleConvertor;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleConvertor articleConvertor;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleConvertor articleConvertor, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.articleConvertor = articleConvertor;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<ArticleDTO> findAllByPage(Integer pageCurrent) {
        List<Article> articles = articleRepository.findAll(pageCurrent);
        return articleConvertor.convert(articles);
    }

    @Override
    @Transactional
    public ArticleDTO findById(String id) {
        Article article = articleRepository.findById(Long.parseLong(id));
        return articleConvertor.convert(article);
    }

    @Override
    public List<ArticleDTO> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articleConvertor.convert(articles);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Article article = articleRepository.findById(id);
        articleRepository.remove(article);
    }

    @Override
    @Transactional
    public void add(Long id, ArticleDTO articleDTO) {
        User user = userRepository.findById(id);
        Article article = articleConvertor.convert(articleDTO);
        article.setUser(user);
        articleRepository.persist(article);
    }
}
