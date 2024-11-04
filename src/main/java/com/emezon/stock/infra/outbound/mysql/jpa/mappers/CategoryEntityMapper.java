package com.emezon.stock.infra.outbound.mysql.jpa.mappers;

import com.emezon.stock.app.dtos.category.CategoryDTO;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.infra.outbound.mysql.jpa.entities.CategoryEntity;


public class CategoryEntityMapper {

    private CategoryEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());
        categoryEntity.setCreatedAt(category.getCreatedAt());
        categoryEntity.setUpdatedAt(category.getUpdatedAt());
        return categoryEntity;
    }

    public static CategoryEntity toEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setDescription(categoryDTO.getDescription());
        return categoryEntity;
    }

    public static Category toModel(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setDescription(categoryEntity.getDescription());
        category.setCreatedAt(categoryEntity.getCreatedAt());
        category.setUpdatedAt(categoryEntity.getUpdatedAt());
        return category;
    }

}
