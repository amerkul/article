package com.example.articlemongo.service;

import com.example.articlemongo.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> retrieveAll();
    Article retrieveById(long articleId);
    void update(Article article);
    void delete(long articleId);

    Article create(Article article);
}
