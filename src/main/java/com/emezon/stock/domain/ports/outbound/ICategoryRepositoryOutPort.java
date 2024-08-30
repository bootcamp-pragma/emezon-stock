package com.emezon.stock.domain.ports.outbound;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepositoryOutPort {

    Category save(Category category);

    Optional<Category> findById(String id);

    Optional<Category> findByName(String name);

    Optional<Category> findByCode(String code);

    PaginatedResponse<Category> findAll(int page, int size, List<String> sorting);

}
