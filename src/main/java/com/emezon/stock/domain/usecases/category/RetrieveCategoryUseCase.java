package com.emezon.stock.domain.usecases.category;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.inbound.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.ports.outbound.ICategoryRepositoryOutPort;

import java.util.Optional;

public class RetrieveCategoryUseCase implements IRetrieveCategoryInPort {

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

    public RetrieveCategoryUseCase(ICategoryRepositoryOutPort categoryRepositoryOutPort) {
        this.categoryRepositoryOutPort = categoryRepositoryOutPort;
    }

    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepositoryOutPort.findById(id);
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepositoryOutPort.findByName(name);
    }

    @Override
    public Optional<Category> getCategoryByCode(String code) {
        return categoryRepositoryOutPort.findByCode(code);
    }

    @Override
    public PaginatedResponse<Category> getAllCategories(PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return categoryRepositoryOutPort.findAll(params);
    }

}
