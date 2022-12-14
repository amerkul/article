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
import java.util.ArrayList;
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
    public ArticleDto getById(@PathVariable("id") String id) {
        return articleDtoBuilder(articleService.retrieveById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") String id) {
        articleService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search/title", produces = "application/json")
    public ArticleDto getByTitle(@RequestParam("title") String title) {
        return articleDtoBuilder(articleService.retrieveByTitle(title));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search/body", produces = "application/json")
    public List<ArticleDto> getByBody(@RequestParam("body") String body) {
        return MapperConfig.convertList(articleService.retrieveByBody(body), this::articleDtoBuilder);
    }


    private Article articleBuilder(ArticleDto articleDto) {
        return modelMapper.map(articleDto, Article.class);
    }

    private ArticleDto articleDtoBuilder(Article article) {
        return modelMapper.map(article, ArticleDto.class);
    }
}
