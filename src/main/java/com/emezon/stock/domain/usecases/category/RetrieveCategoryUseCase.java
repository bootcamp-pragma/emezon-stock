package com.emezon.stock.domain.usecases.category;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.common.constants.CategoryConstraints;
import com.emezon.stock.domain.exceptions.category.CategoryPageNumberInvalidException;
import com.emezon.stock.domain.exceptions.category.CategoryPageSizeInvalidException;
import com.emezon.stock.domain.exceptions.category.CategorySortDirectionInvalidException;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.IRetrieveCategoryInPort;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RetrieveCategoryUseCase implements IRetrieveCategoryInPort {

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

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
        if (page < CategoryConstraints.PAGE_NUMBER_MIN) {
            throw new CategoryPageNumberInvalidException();
        }
        if (size < CategoryConstraints.PAGE_SIZE_MIN) {
            throw new CategoryPageSizeInvalidException();
        }
        if (!CategoryConstraints.SORT_DIRECTIONS.contains(sortDirection)) {
            throw new CategorySortDirectionInvalidException();
        }
    }

}
