package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.domain.models.Article;

public class ArticleDTOMapper {

    private ArticleDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Article toModel(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setName(articleDTO.getName());
        article.setDescription(articleDTO.getDescription());
        article.setPrice(articleDTO.getPrice());
        article.setStock(articleDTO.getStock());
        article.setBrand(BrandDTOMapper.toModel(articleDTO.getBrand()));
        article.setCategories(articleDTO.getCategories().stream().map(CategoryDTOMapper::toModel).toList());
        return article;
    }

    public static ArticleDTO toDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setName(article.getName());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setPrice(article.getPrice());
        articleDTO.setStock(article.getStock());
        articleDTO.setBrand(BrandDTOMapper.toDTO(article.getBrand()));
        articleDTO.setCategories(article.getCategories().stream().map(CategoryDTOMapper::toDTO).toList());
        return articleDTO;
    }

}
