package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleConvertor {
    public Article convert(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setDate(new Date());
        article.setName(articleDTO.getName());
        article.setSummary(articleDTO.getSummary());
        article.setContent(articleDTO.getContent());
        return article;
    }

    public List<ArticleDTO> convert(List<Article> articles) {
        return articles.stream().map(article -> {
            return convert(article);
        }).collect(Collectors.toList());
    }

    public ArticleDTO convert(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        Date date = DateUtils.truncate(article.getDate(),
                java.util.Calendar.DAY_OF_MONTH);
        articleDTO.setDate(String.valueOf(date));
        articleDTO.setName(article.getName());
        articleDTO.setContent(article.getContent());
        articleDTO.setSummary(article.getSummary());
        articleDTO.setNameAuthor(article.getUser().getName());
        articleDTO.setSurnameAuthor(article.getUser().getSurname());
        if (!article.getComments().isEmpty()) {
            articleDTO.setComments(article.getComments());
        }
        return articleDTO;
    }
}
