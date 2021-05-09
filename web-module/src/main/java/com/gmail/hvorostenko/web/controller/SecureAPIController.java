package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SecureAPIController {
    private final ArticleService articleService;

    public SecureAPIController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<ArticleDTO> getArticles() {
        return articleService.findAll();
    }

    @GetMapping("/articles/{id}")
    public ArticleDTO getArticleById(@PathVariable String id) {
        return articleService.findById(id);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/articles/{id}")
    public ResponseEntity<Void> addItem(@PathVariable Long id, @RequestBody ArticleDTO article) {
        articleService.add(id, article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
