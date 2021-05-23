package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.service.ArticleService;
import com.gmail.hvorostenko.service.ItemService;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SecureAPIController {
    private final ArticleService articleService;
    private final ItemService itemService;

    @GetMapping("/articles")
    public List<ArticleDTO> getArticles() {
        return articleService.findAll();
    }

    @GetMapping("/articles/{id}")
    public ArticleDTO getArticleById(@PathVariable String id) {
        return articleService.findById(id);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/articles/{id}")
    public ResponseEntity<Void> addArticle(@PathVariable Long id, @RequestBody ArticleDTO article) {
        articleService.add(id, article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public List<ItemDTO> getItems() {
        return itemService.findAll();
    }

    @GetMapping("/items/{id}")
    public ItemDTO getItemById(@PathVariable String id) {
        return itemService.findById(id);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO) {
        itemService.add(itemDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
