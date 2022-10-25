package com.example.articlemongo.controller;

import com.example.articlemongo.config.MapperConfig;
import com.example.articlemongo.controller.dto.ArticleDto;
import com.example.articlemongo.model.Article;
import com.example.articlemongo.service.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/articles")
public class ArticleController {

    private ArticleService articleService;
    private ModelMapper modelMapper;

    @PostMapping(value = "/article", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto create(@RequestBody @Valid ArticleDto articleDto) {
        Article article = articleService.create(articleBuilder(articleDto));
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
