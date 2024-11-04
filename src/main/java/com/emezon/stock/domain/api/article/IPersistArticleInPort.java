package com.emezon.stock.domain.api.article;

import com.emezon.stock.domain.models.Article;

public interface IPersistArticleInPort {

    Article createArticle(Article article);

    Article addSupply(String id, int quantity);


}
