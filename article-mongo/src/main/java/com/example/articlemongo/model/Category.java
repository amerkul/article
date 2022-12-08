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
public class Category {

    @Id
    private long categoryId;
    private String name;
}
