package com.emezon.stock.infra.output.mysql.jpa.mappers;

import com.emezon.stock.domain.models.Category;
import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class CategoryEntityMapper {

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());
        categoryEntity.setCode(category.getCode());
        return categoryEntity;
    }

    public static Category toModel(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());
        category.setDescription(categoryEntity.getDescription());
        category.setCode(categoryEntity.getCode());
        return category;
    }

    public static Set<Category> toModels(Set<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(CategoryEntityMapper::toModel)
                .collect(Collectors.toSet());
    }

    public static Set<CategoryEntity> toEntities(Set<Category> categories) {
        return categories.stream()
                .map(CategoryEntityMapper::toEntity)
                .collect(Collectors.toSet());
    }

}
