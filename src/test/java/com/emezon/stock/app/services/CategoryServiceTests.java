package com.emezon.stock.app.services;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.usecases.category.CreateCategoryUseCase;
import com.emezon.stock.domain.usecases.category.RetrieveCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {

    @Mock
    private CreateCategoryUseCase createCategoryUseCase;

    @Mock
    private RetrieveCategoryUseCase retrieveCategoryUseCase;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Electronics", "ELECT", "Devices and gadgets");
    }

    @Test
    void createCategory_whenCategoryPropertiesAreValid_thenCategoryIsCreated() {
        when(createCategoryUseCase.createCategory(any())).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        assertNotNull(createdCategory);
        assertEquals(category.getName(), createdCategory.getName());
        assertEquals(category.getCode(), createdCategory.getCode());
        assertEquals(category.getDescription(), createdCategory.getDescription());

        verify(createCategoryUseCase, times(1)).createCategory(any());
    }

    @Test
    void findCategoryById_whenCategoryExists_thenCategoryIsReturned() {
        when(retrieveCategoryUseCase.getCategoryById(any())).thenReturn(Optional.of(category));

        Optional<Category> categoryById = categoryService.getCategoryById("1");

        assertTrue(categoryById.isPresent());
        assertEquals(category.getName(), categoryById.get().getName());

        verify(retrieveCategoryUseCase, times(1)).getCategoryById(any());
    }

    @Test
    void findCategoryById_whenCategoryDoesNotExist_thenEmptyOptionalIsReturned() {
        when(retrieveCategoryUseCase.getCategoryById(any())).thenReturn(Optional.empty());

        Optional<Category> categoryById = categoryService.getCategoryById("1");

        assertTrue(categoryById.isEmpty());

        verify(retrieveCategoryUseCase, times(1)).getCategoryById(any());
    }

    @Test
    void findCategoryByName_whenCategoryExists_thenCategoryIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByName(any())).thenReturn(Optional.of(category));

        Optional<Category> categoryByName = categoryService.getCategoryByName("Electronics");

        assertTrue(categoryByName.isPresent());
        assertEquals(category.getName(), categoryByName.get().getName());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByName(any());
    }

    @Test
    void findCategoryByName_whenCategoryDoesNotExist_thenEmptyOptionalIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByName(any())).thenReturn(Optional.empty());

        Optional<Category> categoryByName = categoryService.getCategoryByName("Electronics");

        assertTrue(categoryByName.isEmpty());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByName(any());
    }

    @Test
    void findCategoryByCode_whenCategoryExists_thenCategoryIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByCode(any())).thenReturn(Optional.of(category));

        Optional<Category> categoryByCode = categoryService.getCategoryByCode("ELECT");

        assertTrue(categoryByCode.isPresent());
        assertEquals(category.getName(), categoryByCode.get().getName());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByCode(any());
    }

    @Test
    void findCategoryByCode_whenCategoryDoesNotExist_thenEmptyOptionalIsReturned() {
        when(retrieveCategoryUseCase.getCategoryByCode(any())).thenReturn(Optional.empty());

        Optional<Category> categoryByCode = categoryService.getCategoryByCode("ELECT");

        assertTrue(categoryByCode.isEmpty());

        verify(retrieveCategoryUseCase, times(1)).getCategoryByCode(any());
    }

    @Test
    void getAllCategories_whenCategoriesExist_thenPaginatedResponseIsReturned() {
        PaginatedResponse<Category> paginatedResponse = new PaginatedResponse<>();
        int page = 0;
        int size = 10;
        paginatedResponse.setTotalElements(1);
        paginatedResponse.setTotalPages(1);
        paginatedResponse.setPage(page);
        paginatedResponse.setSize(size);
        paginatedResponse.setElements(List.of(category));

        when(retrieveCategoryUseCase.getAllCategories(page, size, "asc")).thenReturn(paginatedResponse);

        PaginatedResponse<Category> allCategories = categoryService.getAllCategories(page, size, "asc");

        assertNotNull(allCategories);
        assertEquals(1, allCategories.getTotalElements());
        assertEquals(1, allCategories.getElements().size());
        assertEquals(category.getName(), allCategories.getElements().get(0).getName());

        verify(retrieveCategoryUseCase, times(1)).getAllCategories(page, size, "asc");
    }

}
