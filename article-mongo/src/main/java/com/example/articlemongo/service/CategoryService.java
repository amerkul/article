package com.example.articlemongo.service;

import com.example.articlemongo.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);
    List<Category> retrieveAll();
    Category retrieveById(String categoryId);
    void update(Category category);
    void delete(String categoryId);
}
