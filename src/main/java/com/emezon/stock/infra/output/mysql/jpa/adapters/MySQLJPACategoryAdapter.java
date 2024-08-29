package com.emezon.stock.infra.output.mysql.jpa.adapters;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.outbound.ICategoryRepositoryOutPort;
import com.emezon.stock.infra.output.mysql.jpa.entities.CategoryEntity;
import com.emezon.stock.infra.output.mysql.jpa.mappers.CategoryEntityMapper;
import com.emezon.stock.infra.output.mysql.jpa.repositories.IMySQLJPACategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        Optional<CategoryEntity> categoryEntity = repository.findById(id);
        return categoryEntity.map(CategoryEntityMapper::toModel);
    }

    @Override
    public Optional<Category> findByName(String name) {
        Optional<CategoryEntity> categoryEntity = repository.findByName(name);
        return categoryEntity.map(CategoryEntityMapper::toModel);
    }

    @Override
    public Optional<Category> findByCode(String code) {
        Optional<CategoryEntity> categoryEntity = repository.findByCode(code);
        return categoryEntity.map(CategoryEntityMapper::toModel);
    }

    @Override
    public PaginatedResponse<Category> findAll(int page, int size, List<String> sorting) {
        Sort sort = Sort.unsorted();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> pageRes = repository.findAll(pageable);
        PaginatedResponse<Category> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setItems(CategoryEntityMapper.toModels(pageRes.getContent()));
        paginatedResponse.setPage(pageRes.getNumber());
        paginatedResponse.setSize(pageRes.getSize());
        paginatedResponse.setTotalItems(pageRes.getTotalElements());
        paginatedResponse.setTotalPages(pageRes.getTotalPages());
        return paginatedResponse;
    }

}
