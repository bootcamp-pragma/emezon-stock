package com.emezon.stock.infra.output.mysql.jpa.adapters;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.ports.outbound.IArticleRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.ArticleEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.ArticleEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPAArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
    public PaginatedResponse<Article> findAll(int page, int size, List<String> sorting) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sort : sorting) {
            String[] sortArr = sort.split(",");
            if (sortArr.length == 2) {
                Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortArr[1]), sortArr[0]);
                orders.add(order);
            }
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
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
