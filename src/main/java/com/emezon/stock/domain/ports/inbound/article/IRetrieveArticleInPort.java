package com.emezon.stock.domain.ports.inbound.article;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.models.Article;

import java.util.List;
import java.util.Optional;

public interface IRetrieveArticleInPort {

    Optional<Article> getArticleById(String id);

    PaginatedResponse<Article> getAllArticles(PaginatedResponseParams params);

}
