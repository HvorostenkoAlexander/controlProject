package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.ArticleRepository;
import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.repository.model.User;
import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.converter.ArticleConvertor;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import com.gmail.hvorostenko.service.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleConvertor articleConvertor;
    private final UserRepository userRepository;
    private final PageService<Article> pageService;


    @Override
    @Transactional
    public PageDTO<ArticleDTO> findAllSortDate(Integer pageCurrent) {
        PageDTO<ArticleDTO> pageDTO = new PageDTO();
        List<Article> articles = articleRepository.findAll(pageCurrent);
        List<ArticleDTO> articleDTO = articleConvertor.convert(articles);
        pageDTO.getEntityList().addAll(articleDTO);
        List<Integer> pageNumbers = pageService.countPage(new Article());
        pageDTO.getPageNumbers().addAll(pageNumbers);
        return pageDTO;
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
