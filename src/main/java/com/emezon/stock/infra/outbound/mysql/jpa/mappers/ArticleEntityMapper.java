package com.emezon.stock.infra.outbound.mysql.jpa.mappers;

import com.emezon.stock.domain.models.Article;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.ArticleEntity;

import java.util.HashSet;

public class ArticleEntityMapper {

    private ArticleEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ArticleEntity toEntity(Article article) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(article.getId());
        articleEntity.setName(article.getName());
        articleEntity.setDescription(article.getDescription());
        articleEntity.setPrice(article.getPrice());
        articleEntity.setStock(article.getStock());
        articleEntity.setBrand(BrandEntityMapper.toEntity(article.getBrand()));
        articleEntity.setCategories(new HashSet<>(article.getCategories().stream()
                        .map(CategoryEntityMapper::toEntity).toList()));
        articleEntity.setRestockDate(article.getRestockDate());
        articleEntity.setCreatedAt(article.getCreatedAt());
        articleEntity.setUpdatedAt(article.getUpdatedAt());
        return articleEntity;
    }

    public static Article toModel(ArticleEntity articleEntity) {
        Article article = new Article();
        article.setId(articleEntity.getId());
        article.setName(articleEntity.getName());
        article.setDescription(articleEntity.getDescription());
        article.setPrice(articleEntity.getPrice());
        article.setStock(articleEntity.getStock());
        article.setBrand(BrandEntityMapper.toModel(articleEntity.getBrand()));
        article.setCategories(articleEntity.getCategories().stream()
                        .map(CategoryEntityMapper::toModel).toList());
        article.setRestockDate(articleEntity.getRestockDate());
        article.setCreatedAt(articleEntity.getCreatedAt());
        article.setUpdatedAt(articleEntity.getUpdatedAt());
        return article;
    }

}
