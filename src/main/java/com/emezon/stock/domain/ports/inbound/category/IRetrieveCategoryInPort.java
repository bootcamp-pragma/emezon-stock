package com.emezon.stock.domain.ports.inbound.category;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface IRetrieveCategoryInPort {

    Optional<Category> getCategoryById(String id);

    Optional<Category> getCategoryByName(String name);

    Optional<Category> getCategoryByCode(String code);

    PaginatedResponse<Category> getAllCategories(int page, int size, List<String> sorting);

}
