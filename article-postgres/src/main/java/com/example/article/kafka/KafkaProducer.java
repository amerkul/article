package com.example.article.kafka;

import com.example.article.kafka.model.ArticleChangeModel;
import com.example.article.kafka.model.CategoryChangeModel;
import com.example.article.model.Article;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;
import org.springframework.messaging.support.MessageBuilder;

@Component
@AllArgsConstructor
public class KafkaProducer {

    private Source source;
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public void sendMessage(Article article) {
        logger.debug("Sending Kafka messages {}", article.getTitle());
        CategoryChangeModel categoryChangeModel = new CategoryChangeModel(article.getCategory().getId(),
                article.getCategory().getName());
        ArticleChangeModel articleChangeModel = new ArticleChangeModel(article.getId(),
                article.getTitle(), article.getBody(), categoryChangeModel);
        source.output().send(MessageBuilder.withPayload(articleChangeModel).build());
        logger.debug("End sending");
    }
}
