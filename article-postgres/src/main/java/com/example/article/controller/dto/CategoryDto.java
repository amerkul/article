package com.example.article.controller.dto;

import com.example.article.model.Article;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CategoryDto {

    private long id;

    @Pattern(regexp = "\\w+", message = "The name should contain only symbols.")
    @Size(min = 2, max = 50, message = "The size should be between 2 and 50 symbols")
    private String name;

}
