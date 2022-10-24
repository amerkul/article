package com.example.article.service.impl;

import com.example.article.exception.CustomNotFountException;
import com.example.article.model.Article;
import com.example.article.repository.ArticleRepository;
import com.example.article.service.ArticleService;
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
        articleRepository.save(article);
        return article;
    }

    @Override
    @Transactional
    public List<Article> retrieveAll() {
        logger.debug("Retrieve all articles");
        return articleRepository.findAll();
    }

    @Override
    @Transactional
    public Article retrieveById(long articleId) {
        logger.debug("Retrieve article by id = " + articleId);
        return articleRepository.findById(articleId).orElseThrow(() ->
                new CustomNotFountException("Unable to retrieve an article with id = " + articleId));
    }

    @Override
    @Transactional
    public void update(Article article) {
        logger.debug("Update article - " + article);
        articleRepository.save(article);
    }

    @Override
    @Transactional
    public void delete(long articleId) {
        logger.debug("Delete article by id = " + articleId);
        Article article = new Article();
        article.setId(articleId);
        articleRepository.delete(article);
    }
}
