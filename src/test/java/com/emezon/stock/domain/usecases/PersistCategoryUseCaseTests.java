package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.exceptions.category.*;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.spi.ICategoryRepositoryOutPort;
import com.emezon.stock.domain.usecases.category.PersistCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersistCategoryUseCaseTests {

    @Mock
    private ICategoryRepositoryOutPort categoryRepositoryOutPort;

    @InjectMocks
    private PersistCategoryUseCase persistCategoryUseCase;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Electronics", "ELECT", "Devices and gadgets");
    }

    @Test
    void createCategory_whenCategoryNameIsNull_thenCategoryNameRequiredExceptionIsThrown() {
        category.setName(null);

        assertThrows(CategoryNameRequiredException.class, () -> persistCategoryUseCase.createCategory(category));

        verify(categoryRepositoryOutPort, never()).save(any());
    }

    @Test
    void createCategory_whenCategoryDescriptionIsNull_thenCategoryDescriptionRequiredExceptionIsThrown() {
        category.setDescription(null);

        assertThrows(CategoryDescriptionRequiredException.class, () -> persistCategoryUseCase.createCategory(category));

        verify(categoryRepositoryOutPort, never()).save(any());
    }

    @Test
    void createCategory_whenCategoryNameIsTooLong_thenCategoryNameMaxLengthExceptionIsThrown() {
        category.setName("This is a very long category name that exceeds the maximum length allowed");

        assertThrows(CategoryNameMaxLengthException.class, () -> persistCategoryUseCase.createCategory(category));

        verify(categoryRepositoryOutPort, never()).save(any());
    }

    @Test
    void createCategory_whenCategoryDescriptionIsTooLong_thenCategoryDescriptionMaxLengthExceptionIsThrown() {
        category.setDescription("This is a very long category description that exceeds the maximum length allowed" +
                "This is a very long category description that exceeds the maximum length allowed");

        assertThrows(CategoryDescriptionMaxLengthException.class, () -> persistCategoryUseCase.createCategory(category));

        verify(categoryRepositoryOutPort, never()).save(any());
    }


    @Test
    void createCategory_whenCategoryNameAlreadyExists_thenCategoryNameAlreadyExistsExceptionIsThrown() {
        when(categoryRepositoryOutPort.findByName(category.getName())).thenReturn(Optional.of(category));

        assertThrows(CategoryNameAlreadyExistsException.class, () -> persistCategoryUseCase.createCategory(category));

        verify(categoryRepositoryOutPort, times(1)).findByName(anyString());
        verify(categoryRepositoryOutPort, never()).save(any());
    }

    @Test
    void createCategory_whenCategoryIsValid_thenCategoryIsCreated() {
        when(categoryRepositoryOutPort.findByName(category.getName())).thenReturn(Optional.empty());
        when(categoryRepositoryOutPort.save(category)).thenReturn(category);

        Category createdCategory = persistCategoryUseCase.createCategory(category);

        assertNotNull(createdCategory);
        assertEquals(category.getName(), createdCategory.getName());

        verify(categoryRepositoryOutPort, times(1)).findByName(category.getName());
        verify(categoryRepositoryOutPort, times(1)).save(category);
    }

}
