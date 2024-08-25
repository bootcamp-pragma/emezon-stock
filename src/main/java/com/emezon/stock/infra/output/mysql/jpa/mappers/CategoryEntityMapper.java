package com.emezon.stock.infra.output.mysql.jpa.mappers;

import com.emezon.stock.domain.models.Category;
import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CategoryEntityMapper {

    private CategoryEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

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

    public static List<Category> toModels(List<CategoryEntity> categoryEntities) {
        return categoryEntities.stream()
                .map(CategoryEntityMapper::toModel)
                .collect(Collectors.toList());
    }

}
