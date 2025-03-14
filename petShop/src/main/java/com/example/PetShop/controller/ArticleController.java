package com.example.PetShop.controller;

import com.example.PetShop.repository.Article;
import com.example.PetShop.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(path = "get")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @PostMapping(path = "post")
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }
}
