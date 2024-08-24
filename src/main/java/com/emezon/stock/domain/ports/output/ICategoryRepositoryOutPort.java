package com.emezon.stock.domain.ports.output;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Category;

import java.util.Optional;
import java.util.Set;

public interface ICategoryRepositoryOutPort {

    Category save(Category category);

    Optional<Category> findById(String id);

    Optional<Category> findByName(String name);

    Optional<Category> findByCode(String code);

    PaginatedResponse<Category> findAll(int page, int size);

}
