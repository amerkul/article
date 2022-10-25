package com.example.articlemongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "articles")
public class Category {

    @Id
    private String id;
    private String name;
    private List<Article> articles = new ArrayList<>();

    public Category(String name, List<Article> articles) {
        this.articles = articles;
        this.name = name;
    }
}
