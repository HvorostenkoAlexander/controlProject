package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.service.constant.ArticleConvertorConst.SUMMARY_BEGIN_INDEX_CONST;
import static com.gmail.hvorostenko.service.constant.ArticleConvertorConst.SUMMARY_END_INDEX_CONST;

@Component
public class ArticleConvertor {
    public Article convert(ArticleDTO articleDTO) throws ParseException {
        Article article = new Article();
        article.setId(articleDTO.getId());
        Date date = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH ).parse( articleDTO.getDate() );
        article.setDate(date);
        article.setName(articleDTO.getName());
        if (articleDTO.getContent().length() > SUMMARY_END_INDEX_CONST) {
            article.setSummary((articleDTO.getContent()
                    .substring(SUMMARY_BEGIN_INDEX_CONST, SUMMARY_END_INDEX_CONST)));
        } else {
            article.setSummary((articleDTO.getContent()));
        }
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
        LocalDate date = LocalDate.ofInstant(
                article.getDate().toInstant(), ZoneId.systemDefault());
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

    public Article articleConvert(Article article, ArticleDTO articleDTO) {
        article.setDate(new Date());
        article.setName(articleDTO.getName());
        article.setContent(articleDTO.getContent());
        return article;
    }
}
