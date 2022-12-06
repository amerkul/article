package com.example.articlemongo.controller.dto;


import com.example.articlemongo.model.Category;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ArticleDto {


    private long articleId;

    @Pattern(regexp = "\\w+", message = "The title should contain only symbols.")
    @Size(min = 2, max = 50, message = "The size should be between 2 and 50 symbols")
    private String title;

    @Size(min = 1, max = 2000, message = "The size should be between 1 and 2000 symbols")
    private String body;

    private Category category;
}
