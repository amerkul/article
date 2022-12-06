package com.example.article.kafka.model;

import lombok.*;

@AllArgsConstructor
@Getter @Setter @ToString
@NoArgsConstructor
public class ArticleChangeModel {

    private long id;
    private String title;
    private String body;
    private CategoryChangeModel categoryChangeModel;
}
