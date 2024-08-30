package com.emezon.stock.domain.ports.inbound.article;

import com.emezon.stock.domain.models.Article;

public interface ICreateArticleInPort {

    Article createArticle(Article article);

}
