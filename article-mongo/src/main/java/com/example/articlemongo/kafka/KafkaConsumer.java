package com.example.articlemongo.kafka;

import com.example.articlemongo.kafka.model.ArticleChangeModel;
import com.example.articlemongo.model.Article;
import com.example.articlemongo.model.Category;
import com.example.articlemongo.service.ArticleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@AllArgsConstructor
@EnableBinding(CustomChannel.class)
public class KafkaConsumer {

    private ArticleService articleService;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @StreamListener("inboundOrgChanges")
    public void loggerSink(ArticleChangeModel articleChangeModel) {
        logger.debug("Article change model is received " + articleChangeModel);
        Category category = new Category(articleChangeModel.getCategoryChangeModel().getId(),
                articleChangeModel.getCategoryChangeModel().getName());
        Article article = new Article(String.valueOf(articleChangeModel.getId()), articleChangeModel.getTitle(),
                articleChangeModel.getBody(), category);
        articleService.create(article);
    }
}
