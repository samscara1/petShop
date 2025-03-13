package com.example.PetShop.service;

import com.example.PetShop.repository.Article;
import com.example.PetShop.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    private final ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
