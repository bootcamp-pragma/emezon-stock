package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;
import com.emezon.stock.domain.models.Category;
import com.emezon.stock.domain.ports.outbound.ICategoryRepositoryOutPort;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveCategoryUseCaseTests {

    @Mock
    private ICategoryRepositoryOutPort categoryRepositoryOutPort;

    @InjectMocks
    private RetrieveCategoryUseCase retrieveCategoryUseCase;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("123", "Electronics", "ELECT", "Devices and gadgets");
    }

    @Test
    void getCategoryById_whenCategoryIdExists_thenCategoryIsReturned() {
        when(categoryRepositoryOutPort.findById(category.getId())).thenReturn(Optional.of(category));

        Optional<Category> result = retrieveCategoryUseCase.getCategoryById(category.getId());

        assertTrue(result.isPresent());
        Category categoryResult = result.get();
        assertEquals(category.getId(), categoryResult.getId());

        verify(categoryRepositoryOutPort, times(1)).findById(category.getId());
    }

    @Test
    void getCategoryById_whenCategoryIdDoesNotExist_thenCategoryIsNotReturned() {
        when(categoryRepositoryOutPort.findById(category.getId())).thenReturn(Optional.empty());

        Optional<Category> result = retrieveCategoryUseCase.getCategoryById(category.getId());

        assertTrue(result.isEmpty());

        verify(categoryRepositoryOutPort, times(1)).findById(category.getId());
    }

    @Test
    void getCategoryByName_whenCategoryNameExists_thenCategoryIsReturned() {
        when(categoryRepositoryOutPort.findByName(category.getName())).thenReturn(Optional.of(category));

        Optional<Category> result = retrieveCategoryUseCase.getCategoryByName(category.getName());

        assertTrue(result.isPresent());
        Category categoryResult = result.get();
        assertEquals(category.getName(), categoryResult.getName());

        verify(categoryRepositoryOutPort, times(1)).findByName(category.getName());
    }

    @Test
    void getCategoryByName_whenCategoryNameDoesNotExist_thenCategoryIsNotReturned() {
        when(categoryRepositoryOutPort.findByName(category.getName())).thenReturn(Optional.empty());

        Optional<Category> result = retrieveCategoryUseCase.getCategoryByName(category.getName());

        assertTrue(result.isEmpty());

        verify(categoryRepositoryOutPort, times(1)).findByName(category.getName());
    }

    @Test
    void getCategoryByCode_whenCategoryCodeExists_thenCategoryIsReturned() {
        when(categoryRepositoryOutPort.findByCode(category.getCode())).thenReturn(Optional.of(category));

        Optional<Category> result = retrieveCategoryUseCase.getCategoryByCode(category.getCode());

        assertTrue(result.isPresent());
        Category categoryResult = result.get();
        assertEquals(category.getCode(), categoryResult.getCode());

        verify(categoryRepositoryOutPort, times(1)).findByCode(category.getCode());
    }

    @Test
    void getCategoryByCode_whenCategoryCodeDoesNotExist_thenCategoryIsNotReturned() {
        when(categoryRepositoryOutPort.findByCode(category.getCode())).thenReturn(Optional.empty());

        Optional<Category> result = retrieveCategoryUseCase.getCategoryByCode(category.getCode());

        assertTrue(result.isEmpty());

        verify(categoryRepositoryOutPort, times(1)).findByCode(category.getCode());
    }

    @Test
    void getAllCategories_whenPageNumberIsInvalid_thenPaginatedResponsePageNumberInvalidExceptionIsThrown() {
        PaginatedResponseParams params = new PaginatedResponseParams(-1, 10, List.of("name,asc"));
        assertThrows(PaginatedResponsePageNumberInvalidException.class, () -> retrieveCategoryUseCase.getAllCategories(params));

        verify(categoryRepositoryOutPort, never()).findAll(any());
    }

    @Test
    void getAllCategories_whenPageSizeIsInvalid_thenPaginatedResponsePageSizeInvalidExceptionIsThrown() {
        PaginatedResponseParams params = new PaginatedResponseParams(1, 0, List.of("name,asc"));
        assertThrows(PaginatedResponsePageSizeInvalidException.class, () -> retrieveCategoryUseCase.getAllCategories(params));

        verify(categoryRepositoryOutPort, never()).findAll(any());
    }

    @Test
    void getAllCategories_whenSortDirectionIsInvalid_thenPaginatedResponseSortDirectionInvalidExceptionIsThrown() {
        PaginatedResponseParams params = new PaginatedResponseParams(1, 10, List.of("name,invalid"));
        assertThrows(PaginatedResponseSortDirectionInvalidException.class, () -> retrieveCategoryUseCase.getAllCategories(params));

        verify(categoryRepositoryOutPort, never()).findAll(any());
    }

    @Test
    void getAllCategories_whenParametersAreValid_thenCategoriesAreReturned() {
        PaginatedResponse<Category> paginatedResponse = new PaginatedResponse<>();
        int page = 0;
        int size = 10;
        paginatedResponse.setPage(0);
        paginatedResponse.setSize(size);
        paginatedResponse.setItems(List.of(category));
        paginatedResponse.setTotalItems(1);
        paginatedResponse.setTotalPages(1);

        List<String> sorting = List.of("name,desc");
        PaginatedResponseParams params = new PaginatedResponseParams(page, size, sorting);
        when(categoryRepositoryOutPort.findAll(params)).thenReturn(paginatedResponse);

        PaginatedResponse<Category> result = retrieveCategoryUseCase.getAllCategories(params);

        assertNotNull(result);
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(1, result.getTotalItems());
        assertEquals(1, result.getTotalPages());
        assertEquals(category.getId(), result.getItems().get(0).getId());

        verify(categoryRepositoryOutPort, times(1)).findAll(params);
    }

}
