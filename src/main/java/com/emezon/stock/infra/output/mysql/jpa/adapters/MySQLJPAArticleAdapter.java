package com.emezon.stock.infra.output.mysql.jpa.adapters;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.ArticleEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.ArticleEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPAArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MySQLJPAArticleAdapter implements IArticleRepositoryOutPort {

    private final IMySQLJPAArticleRepository repository;

    @Override
    public Article save(Article article) {
        ArticleEntity articleEntity = ArticleEntityMapper.toEntity(article);
        ArticleEntity savedArticleEntity = repository.save(articleEntity);
        return ArticleEntityMapper.toModel(savedArticleEntity);
    }

    @Override
    public Optional<Article> findById(String id) {
        return Optional.empty();
    }

    @Override
    public PaginatedResponse<Article> findAll(int page, int size, List<String> sorting) {
        return null;
    }
}
