package com.emezon.stock.domain.ports.input.category;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Category;

import java.util.Optional;
import java.util.Set;

public interface IRetrieveCategoryInPort {

    Optional<Category> getCategoryById(String id);

    Optional<Category> getCategoryByName(String name);

    Optional<Category> getCategoryByCode(String code);

    PaginatedResponse<Category> getAllCategories(int page, int size);

}
