package com.example.article.service;

import com.example.article.model.Article;

import java.util.List;

public interface CategoryService {

    Article create(Article category);
    List<Article> retrieveAll();
    Article retrieveById(long categoryId);
    void update(Article category);
    void delete(long categoryId);
}
