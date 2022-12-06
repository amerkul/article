package com.example.article.controller;

import com.example.article.config.MapperConfig;
import com.example.article.controller.dto.ArticleDto;
import com.example.article.model.Article;
import com.example.article.service.ArticleService;
import com.example.article.service.CategoryService;
import com.example.article.service.impl.ArticleServiceImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/articles/{categoryId}")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private ArticleService articleService;
    private CategoryService categoryService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/article", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto create(@RequestBody @Valid ArticleDto articleDto,
                             @PathVariable("categoryId") long categoryId) {
        Article article = articleService.create(articleBuilder(articleDto), categoryId);
        return articleDtoBuilder(article);
    }

    @PutMapping(value = "/article", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@RequestBody @Valid ArticleDto articleDto) {
        articleService.update(articleBuilder(articleDto));
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleDto> getAll() {
        return MapperConfig.convertList(articleService.retrieveAll(), this::articleDtoBuilder);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDto getById(@PathVariable("id") long id) {
        return articleDtoBuilder(articleService.retrieveById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") long id) {
        articleService.delete(id);
    }


    private Article articleBuilder(ArticleDto articleDto) {
        return modelMapper.map(articleDto, Article.class);
    }

    private ArticleDto articleDtoBuilder(Article article) {
        return modelMapper.map(article, ArticleDto.class);
    }
}
