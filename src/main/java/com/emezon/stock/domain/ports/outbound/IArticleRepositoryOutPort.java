package com.emezon.stock.domain.ports.outbound;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Article;

import java.util.List;
import java.util.Optional;

public interface IArticleRepositoryOutPort {

    Article save(Article article);

    Optional<Article> findById(String id);

    PaginatedResponse<Article> findAll(int page, int size, List<String> sorting);

}
