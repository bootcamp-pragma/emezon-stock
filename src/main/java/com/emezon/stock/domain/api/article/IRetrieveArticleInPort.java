package com.emezon.stock.domain.api.article;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Article;

import java.util.Optional;

public interface IRetrieveArticleInPort {

    Optional<Article> getArticleById(String id);

    Optional<Article> getArticleByName(String name);

    PaginatedResponse<Article> getAllArticles(PaginatedResponseParams params);

}
