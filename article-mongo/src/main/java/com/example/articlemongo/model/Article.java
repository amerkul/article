package com.example.articlemongo.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private long articleId;
    private String title;
    private String body;
}
