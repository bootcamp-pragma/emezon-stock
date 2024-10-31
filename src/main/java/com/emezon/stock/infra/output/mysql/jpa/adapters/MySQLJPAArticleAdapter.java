package com.emezon.stock.infra.output.mysql.jpa.adapters;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.spi.IArticleRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.ArticleEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.ArticleEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPAArticleRepository;
import com.emezon.stock.infra.output.mysql.jpa.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        Optional<ArticleEntity> articleEntity = repository.findById(id);
        return articleEntity.map(ArticleEntityMapper::toModel);
    }

    @Override
    public PaginatedResponse<Article> findAll(PaginatedResponseParams params) {
        Pageable pageable = PageableUtils.getFromPaginatedResponseParams(params);
        Page<ArticleEntity> pageRes = repository.findAll(pageable);
        PaginatedResponse<Article> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setItems(pageRes.getContent().stream().map(ArticleEntityMapper::toModel).toList());
        paginatedResponse.setTotalItems(pageRes.getTotalElements());
        paginatedResponse.setTotalPages(pageRes.getTotalPages());
        paginatedResponse.setPage(pageRes.getNumber());
        paginatedResponse.setSize(pageRes.getSize());
        return paginatedResponse;
    }
}
