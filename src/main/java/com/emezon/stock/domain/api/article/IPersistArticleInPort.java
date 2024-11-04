package com.emezon.stock.domain.api.article;

import com.emezon.stock.domain.models.Article;

import java.time.LocalDateTime;

public interface IPersistArticleInPort {

    Article createArticle(Article article);

    Article addSupply(String id, int quantity);

    Article updateRestockDate(String id, LocalDateTime restockDate);


}
