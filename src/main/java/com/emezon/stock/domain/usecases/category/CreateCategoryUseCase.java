package com.emezon.stock.domain.usecases.category;

import com.emezon.stock.domain.constants.CategoryConstraints;
import com.emezon.stock.domain.exceptions.category.*;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.api.category.ICreateCategoryInPort;
import com.emezon.stock.domain.spi.ICategoryRepositoryOutPort;

import java.util.Optional;

public class CreateCategoryUseCase implements ICreateCategoryInPort {

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

    public CreateCategoryUseCase(ICategoryRepositoryOutPort categoryRepositoryOutPort) {
        this.categoryRepositoryOutPort = categoryRepositoryOutPort;
    }

    @Override
    public Category createCategory(Category category) {
        Category processedCategory = processAndValidateCategory(category);
        Optional<Category> categoryByName = categoryRepositoryOutPort.findByName(processedCategory.getName());
        if (categoryByName.isPresent()) {
            throw new CategoryNameAlreadyExistsException(processedCategory.getName());
        }
        Optional<Category> categoryByCode = categoryRepositoryOutPort.findByCode(processedCategory.getCode());
        if (categoryByCode.isPresent()) {
            throw new CategoryCodeAlreadyExistsException(processedCategory.getCode());
        }
        return categoryRepositoryOutPort.save(processedCategory);
    }

    private Category processAndValidateCategory(Category category) {
        if (category.getName() == null) { throw new CategoryNameRequiredException(); }
        category.setName(category.getName().trim());
        if (category.getName().length() > CategoryConstraints.NAME_MAX_LENGTH) {
            throw new CategoryNameMaxLengthException(CategoryConstraints.NAME_MAX_LENGTH);
        }
        if (category.getCode() == null) { throw new CategoryCodeRequiredException(); }
        category.setCode(category.getCode().trim());
        if (category.getDescription() == null) { throw new CategoryDescriptionRequiredException(); }
        category.setDescription(category.getDescription().trim());
        if (category.getDescription().length() > CategoryConstraints.DESCRIPTION_MAX_LENGTH) {
            throw new CategoryDescriptionMaxLengthException(CategoryConstraints.DESCRIPTION_MAX_LENGTH);
        }
        return category;
    }

}
