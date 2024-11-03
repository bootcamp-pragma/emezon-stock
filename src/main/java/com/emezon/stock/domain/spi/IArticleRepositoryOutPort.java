package com.emezon.stock.domain.spi;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Article;

import java.util.Optional;

public interface IArticleRepositoryOutPort {

    Article save(Article article);

    Optional<Article> findById(String id);

    PaginatedResponse<Article> findAll(PaginatedResponseParams params);

}
