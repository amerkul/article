package com.example.articlemongo.service;

import com.example.articlemongo.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> retrieveAll();
    Article retrieveById(String articleId);
    void update(Article article);
    void delete(String articleId);

    Article create(Article article);
}
