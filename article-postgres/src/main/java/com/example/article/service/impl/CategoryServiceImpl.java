package com.example.article.service.impl;

import com.example.article.exception.CustomNotFountException;
import com.example.article.model.Article;
import com.example.article.repository.CategoryRepository;
import com.example.article.service.CategoryService;
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
    public Article create(Article category) {
        logger.debug("Create category; category - "+ category);
        categoryRepository.save(category);
        return category;
    }

    @Override
    @Transactional
    public List<Article> retrieveAll() {
        logger.debug("Retrieve all categories");
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Article retrieveById(long categoryId) {
        logger.debug("Retrieve category by id = " + categoryId);
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new CustomNotFountException("Unable to retrieve an article with id = " + categoryId));
    }

    @Override
    @Transactional
    public void update(Article category) {
        logger.debug("Update category - " + category);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(long categoryId) {
        logger.debug("Delete category by id = " + categoryId);
        Article category = new Article();
        category.setId(categoryId);
        categoryRepository.delete(category);
    }
}
