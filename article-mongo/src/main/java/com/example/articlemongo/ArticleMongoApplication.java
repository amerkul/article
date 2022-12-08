package com.example.articlemongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@RefreshScope
public class ArticleMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleMongoApplication.class, args);
    }

}
