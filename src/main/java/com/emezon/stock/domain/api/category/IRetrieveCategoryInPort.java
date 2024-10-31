package com.emezon.stock.domain.api.category;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Category;

import java.util.Optional;

public interface IRetrieveCategoryInPort {

    Optional<Category> getCategoryById(String id);

    Optional<Category> getCategoryByName(String name);

    Optional<Category> getCategoryByCode(String code);

    PaginatedResponse<Category> getAllCategories(PaginatedResponseParams params);

}
