package com.emezon.stock.domain.spi;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Category;

import java.util.Optional;

public interface ICategoryRepositoryOutPort {

    Category save(Category category);

    Optional<Category> findById(String id);

    Optional<Category> findByName(String name);

    Optional<Category> findByCode(String code);

    PaginatedResponse<Category> findAll(PaginatedResponseParams params);

}
