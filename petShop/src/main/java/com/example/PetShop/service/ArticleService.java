package com.example.PetShop.service;

import com.example.PetShop.repository.Article;
import com.example.PetShop.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    private final ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(Article article) {
        Optional<Article> optionalArticle =  articleRepository.findBySku(article.getSku());
        if (optionalArticle.isPresent()) {
            throw new IllegalStateException("Article already exists");
        }
        return this.articleRepository.save(article);
    }
}
