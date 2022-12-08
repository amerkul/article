package com.example.article.controller;

import com.example.article.config.MapperConfig;
import com.example.article.controller.dto.CategoryDto;
import com.example.article.model.Article;
import com.example.article.model.Category;
import com.example.article.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value ="categories")
public class CategoryController {

    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/category", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryService.create(categoryBuilder(categoryDto));
        return categoryDtoBuilder(category);
    }

    @PutMapping(value = "/category", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody @Valid CategoryDto categoryDto) {
        categoryService.update(categoryBuilder(categoryDto));
    }
    @GetMapping(value = "/all", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAll() {
        return MapperConfig.convertList(categoryService.retrieveAll(), this::categoryDtoBuilder);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getById(@PathVariable("id") long id) {
        return categoryDtoBuilder(categoryService.retrieveById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") long id) {
        categoryService.delete(id);
    }


    private Category categoryBuilder(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto categoryDtoBuilder(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
