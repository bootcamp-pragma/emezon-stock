package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.article.ArticleDTO;
import com.emezon.stock.app.dtos.article.ArticleListDTO;
import com.emezon.stock.app.dtos.article.CreateArticleDTO;
import com.emezon.stock.domain.models.Article;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.models.Category;

import java.util.ArrayList;
import java.util.List;

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

    public static Article toModel(CreateArticleDTO createArticleDTO) {
        Article article = new Article();
        article.setName(createArticleDTO.getName());
        article.setDescription(createArticleDTO.getDescription());
        article.setPrice(createArticleDTO.getPrice());
        article.setStock(createArticleDTO.getStock());
        Brand brand = new Brand();
        brand.setId(createArticleDTO.getBrandId());
        article.setBrand(brand);
        List<Category> categories = new ArrayList<>();
        for (String categoryId : createArticleDTO.getCategoryIds()) {
            Category category = new Category();
            category.setId(categoryId);
            categories.add(category);
        }
        article.setCategories(categories);
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

    public static ArticleListDTO toListDTO(Article article) {
        ArticleListDTO articleListDTO = new ArticleListDTO();
        articleListDTO.setId(article.getId());
        articleListDTO.setName(article.getName());
        articleListDTO.setDescription(article.getDescription());
        articleListDTO.setPrice(article.getPrice());
        articleListDTO.setStock(article.getStock());
        articleListDTO.setBrand(BrandDTOMapper.toDTO(article.getBrand()));
        articleListDTO.setCategories(article.getCategories().stream().map(CategoryDTOMapper::toIdNameDTO).toList());
        return articleListDTO;
    }

}
