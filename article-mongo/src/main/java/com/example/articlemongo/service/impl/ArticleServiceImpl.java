package com.example.articlemongo.service.impl;

import com.example.articlemongo.exception.CustomNotFountException;
import com.example.articlemongo.model.Article;
import com.example.articlemongo.repository.ArticleRepository;
import com.example.articlemongo.service.ArticleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public Article create(Article article) {
        logger.debug("Create article; article - " + article);
        articleRepository.insert(article);
        return article;
    }

    @Override
    @Transactional
    public List<Article> retrieveAll() {
        List<Article> articles = articleRepository.findAll();
        logger.debug("Retrieve all articles" + articles);
        return articles;
    }

    @Override
    @Transactional
    public Article retrieveById(String articleId) {
        logger.debug("Retrieve article by id = " + articleId);
        return articleRepository.findById(articleId).orElseThrow(() ->
                new CustomNotFountException("Article with id = " + articleId + "not found"));
    }

    @Override
    @Transactional
    public void update(Article article) {
        logger.debug("Update article - " + article);
        articleRepository.save(article);
    }

    @Override
    @Transactional
    public void delete(String articleId) {
        logger.debug("Delete article by id = " + articleId);
        Article article = new Article();
        article.setArticleId(articleId);
        articleRepository.delete(article);
    }

    @Override
    public Article retrieveByTitle(String title) {
        return articleRepository.findArticlesByTitle(title);
    }

    @Override
    public List<Article> retrieveByBody(String body) {
        return articleRepository.findArticlesByBody(".*" + body  + ".*");
    }
}
