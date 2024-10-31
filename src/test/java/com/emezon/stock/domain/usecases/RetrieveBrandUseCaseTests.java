package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.spi.IBrandRepositoryOutPort;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveBrandUseCaseTests {

    @Mock
    private IBrandRepositoryOutPort brandRepositoryOutPort;

    @InjectMocks
    private RetrieveBrandUseCase retrieveBrandUseCase;

    private Brand brand;

    @BeforeEach
    public void setUp() {
        brand = new Brand("123", "SAMS", "Samsung Electronics");
    }

    @Test
    void getBrandById_whenBrandIdExists_thenBrandIsReturned() {
        when(brandRepositoryOutPort.findById(brand.getId())).thenReturn(Optional.of(brand));

        Optional<Brand> result = retrieveBrandUseCase.getBrandById(brand.getId());

        assertTrue(result.isPresent());
        Brand brandResult = result.get();
        assertEquals(brand.getName(), brandResult.getName());

        verify(brandRepositoryOutPort, times(1)).findById(brand.getId());
    }

    @Test
    void getBrandById_whenBrandIdDoesNotExist_thenBrandIsNotReturned() {
        when(brandRepositoryOutPort.findById(brand.getId())).thenReturn(Optional.empty());

        Optional<Brand> result = retrieveBrandUseCase.getBrandById(brand.getId());

        assertTrue(result.isEmpty());

        verify(brandRepositoryOutPort, times(1)).findById(brand.getId());
    }

    @Test
    void getBrandByName_whenBrandNameExists_thenBrandIsReturned() {
        when(brandRepositoryOutPort.findByName(brand.getName())).thenReturn(Optional.of(brand));

        Optional<Brand> result = retrieveBrandUseCase.getBrandByName(brand.getName());

        assertTrue(result.isPresent());
        Brand brandResult = result.get();
        assertEquals(brand.getName(), brandResult.getName());

        verify(brandRepositoryOutPort, times(1)).findByName(brand.getName());
    }

    @Test
    void getBrandByName_whenBrandNameDoesNotExist_thenBrandIsNotReturned() {
        when(brandRepositoryOutPort.findByName(brand.getName())).thenReturn(Optional.empty());

        Optional<Brand> result = retrieveBrandUseCase.getBrandByName(brand.getName());

        assertTrue(result.isEmpty());

        verify(brandRepositoryOutPort, times(1)).findByName(brand.getName());
    }

    @Test
    void getAllBrands_whenParametersAreValid_thenAllBrandsAreReturned() {
        PaginatedResponse<Brand> paginatedResponse = new PaginatedResponse<>();
        int page = 0;
        int size = 10;
        paginatedResponse.setPage(0);
        paginatedResponse.setSize(size);
        paginatedResponse.setItems(List.of(brand));
        paginatedResponse.setTotalItems(1);
        paginatedResponse.setTotalPages(1);

        List<String> sorting = List.of("name,desc");
        PaginatedResponseParams params = new PaginatedResponseParams(page, size, sorting);
        when(brandRepositoryOutPort.findAll(params)).thenReturn(paginatedResponse);

        PaginatedResponse<Brand> result = retrieveBrandUseCase.getAllBrands(params);
        assertNotNull(result);
        assertEquals(page, result.getPage());
        assertEquals(size, result.getSize());
        assertEquals(1, result.getTotalItems());
        assertEquals(1, result.getTotalPages());
        assertEquals(brand.getId(), result.getItems().get(0).getId());

        verify(brandRepositoryOutPort, times(1)).findAll(params);
    }

}
