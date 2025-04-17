package com.example.PetShop.service;

import com.example.PetShop.repository.Article;
import com.example.PetShop.repository.ArticleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Article createArticle(Article article) {
        Optional<Article> optionalArticle =  articleRepository.findBySku(article.getSku());
        if (optionalArticle.isPresent()) {
            throw new IllegalStateException("Article already exists");
        }
        return this.articleRepository.save(article);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        Optional<Article> ById = articleRepository.findById(id);
        if (ById.isEmpty()) {
            throw new IllegalStateException("Article does not exist");
        }
        articleRepository.deleteById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void update(Long id, String sku, String title, String description, Integer price) {
        Optional<Article> ById = articleRepository.findById(id);
        if (ById.isEmpty()) {
            throw new IllegalStateException("Article does not exist");
        }
        Article article = ById.get();
        boolean updated = false;

        if (sku != null && !sku.equals(article.getSku())) {
            article.setSku(sku);
            updated = true;
        }
        if (title != null && !title.equals(article.getTitle())) {
            article.setTitle(title);
            updated = true;
        }
        if (description != null && !description.equals(article.getDescription())) {
            article.setDescription(description);
            updated = true;
        }
        if (price != null && price > 0 && price != article.getPrice()) {
            article.setPrice(price);
            updated = true;
        }

        if (updated) {
            articleRepository.save(article);
        }

    }
}
