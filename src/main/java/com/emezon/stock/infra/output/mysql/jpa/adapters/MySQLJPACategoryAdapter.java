package com.emezon.stock.infra.output.mysql.jpa.adapters;

import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.CategoryEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPACategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MySQLJPACategoryAdapter implements ICategoryRepositoryOutPort {

    private final IMySQLJPACategoryRepository repository;

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = CategoryEntityMapper.toEntity(category);
        CategoryEntity savedCategoryEntity = repository.save(categoryEntity);
        return CategoryEntityMapper.toModel(savedCategoryEntity);
    }

    @Override
    public Optional<Category> findById(String id) {
        CategoryEntity categoryEntity = repository.findById(id).orElse(null);
        if (categoryEntity == null) {
            return Optional.empty();
        }
        return Optional.of(CategoryEntityMapper.toModel(categoryEntity));
    }

    @Override
    public Optional<Category> findByName(String name) {
        CategoryEntity categoryEntity = repository.findByName(name);
        if (categoryEntity == null) {
            return Optional.empty();
        }
        return Optional.of(CategoryEntityMapper.toModel(categoryEntity));
    }

    @Override
    public Optional<Category> findByCode(String code) {
        CategoryEntity categoryEntity = repository.findByCode(code);
        if (categoryEntity == null) {
            return Optional.empty();
        }
        return Optional.of(CategoryEntityMapper.toModel(categoryEntity));
    }

    @Override
    public Set<Category> findAll() {
        Set<CategoryEntity> categoryEntities = new HashSet<>(repository.findAll());
        return CategoryEntityMapper.toModels(categoryEntities);
    }

}
