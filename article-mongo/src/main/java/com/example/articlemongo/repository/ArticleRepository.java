package com.example.articlemongo.repository;

import com.example.articlemongo.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    @Query("{ 'title' : ?0 }")
    Article findArticlesByTitle(String title);

    @Query("{'body': { $regex: ?0, $options: 'i'}}")
    List<Article> findArticlesByBody(String regexp);
}
