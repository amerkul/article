package com.example.article.service;

import com.example.article.model.Article;
import com.example.article.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);
    List<Category> retrieveAll();
    Category retrieveById(long categoryId);
    void update(Category category);
    void delete(long categoryId);
}
