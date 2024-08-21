package com.emezon.stock.app.usecases.category;

import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.input.category.ICreateCategoryInPort;
import com.emezon.stock.domain.ports.output.ICategoryRepositoryOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCategoryUseCase implements ICreateCategoryInPort {

    private final ICategoryRepositoryOutPort categoryRepositoryOutPort;

    @Override
    public Category createCategory(Category category) {
        return categoryRepositoryOutPort.save(category);
    }

}
