package com.example.articlemongo.service.impl;

import com.example.articlemongo.exception.CustomNotFountException;
import com.example.articlemongo.model.Category;
import com.example.articlemongo.repository.CategoryRepository;
import com.example.articlemongo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category create(Category category) {
        logger.debug("Create category; category - "+ category);
        categoryRepository.save(category);
        return category;
    }

    @Override
    @Transactional
    public List<Category> retrieveAll() {
        logger.debug("Retrieve all categories");
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category retrieveById(String categoryId) {
        logger.debug("Retrieve category by id = " + categoryId);
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new CustomNotFountException("Category with id = " + categoryId + " is not found"));
    }

    @Override
    @Transactional
    public void update(Category category) {
        logger.debug("Update category - " + category);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(String categoryId) {
        logger.debug("Delete category by id = " + categoryId);
        Category category = new Category();
        category.setId(categoryId);
        categoryRepository.delete(category);
    }
}
