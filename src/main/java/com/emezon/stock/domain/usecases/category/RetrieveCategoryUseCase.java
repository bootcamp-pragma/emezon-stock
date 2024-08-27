package com.emezon.stock.domain.usecases.category;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.common.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;
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
    public PaginatedResponse<Category> getAllCategories(int page, int size, String sortDirection) {
        sortDirection = sortDirection.toLowerCase().trim();
        validateParameters(page, size, sortDirection);
        return categoryRepositoryOutPort.findAll(page, size, sortDirection);
    }

    private void validateParameters(int page, int size, String sortDirection) {
        if (page < PaginatedResponseConstraints.PAGE_NUMBER_MIN) {
            throw new PaginatedResponsePageNumberInvalidException();
        }
        if (size < PaginatedResponseConstraints.PAGE_SIZE_MIN) {
            throw new PaginatedResponsePageSizeInvalidException();
        }
        if (!PaginatedResponseConstraints.SORT_DIRECTIONS.contains(sortDirection)) {
            throw new PaginatedResponseSortDirectionInvalidException();
        }
    }

}
