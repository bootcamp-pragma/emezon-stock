package com.emezon.stock.domain.api.article;

import com.emezon.stock.domain.models.Article;

import java.time.LocalDateTime;
import java.util.Map;

public interface IPersistArticleInPort {

    Article createArticle(Article article);

    Article addSupply(String id, int quantity, LocalDateTime restockDate);

    Article addSupply(String id, Map<String, Object> payload);

    Article updateRestockDate(String id, LocalDateTime restockDate);

}
