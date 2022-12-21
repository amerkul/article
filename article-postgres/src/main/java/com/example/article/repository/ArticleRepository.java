package com.example.article.repository;

import com.example.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, CrudRepository<Article, Long> {

    @Modifying
    @Query(value = "CALL delete_article(:articleId);", nativeQuery = true)
    void deleteArticleById(@Param("articleId") long articleId);

    @Query(value = "SELECT get_article_count();", nativeQuery = true)
    int countArticles();
}
