package com.example.articlemongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "articles")
public class Article {

    @Id
    private String articleId;
    private String title;
    private String body;
    private Category category;
}
