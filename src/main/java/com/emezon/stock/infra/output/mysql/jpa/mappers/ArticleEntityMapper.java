package com.emezon.stock.infra.output.mysql.jpa.mappers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.infra.output.mysql.jpa.entities.ArticleEntity;

import java.util.HashSet;

public class ArticleEntityMapper {

    private ArticleEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ArticleEntity toEntity(ArticleDTO articleDTO) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setName(articleDTO.getName());
        articleEntity.setDescription(articleDTO.getDescription());
        articleEntity.setPrice(articleDTO.getPrice());
        articleEntity.setStock(articleDTO.getStock());
        articleEntity.setBrand(BrandEntityMapper.toEntity(articleDTO.getBrand()));
        articleEntity.setCategories(new HashSet<>(articleDTO.getCategories().stream()
                        .map(CategoryEntityMapper::toEntity).toList()));
        return articleEntity;
    }

}
