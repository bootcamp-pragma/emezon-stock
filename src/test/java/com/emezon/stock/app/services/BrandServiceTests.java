package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.BrandDTO;
import com.emezon.stock.app.dtos.CreateBrandDTO;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceTests {

    @Mock
    private CreateBrandUseCase createBrandUseCase;

    @Mock
    private RetrieveBrandUseCase retrieveBrandUseCase;

    @InjectMocks
    private BrandService brandService;

    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand("Samsung", "Samsung Electronics Co., Ltd.");
    }

    @Test
    void createBrand_whenBrandPropertiesAreValid_thenBrandIsCreated() {
        when(createBrandUseCase.createBrand(any())).thenReturn(brand);

        CreateBrandDTO brandDTO = new CreateBrandDTO("Samsung", "Samsung Electronics Co., Ltd.");
        brandDTO.init();
        BrandDTO createdBrand = brandService.createBrand(brandDTO);

        assertNotNull(createdBrand);
        assertEquals(brand.getName(), createdBrand.getName());
        assertEquals(brand.getDescription(), createdBrand.getDescription());

        verify(createBrandUseCase, times(1)).createBrand(any());
    }

    @Test
    void findBrandById_whenBrandExists_thenBrandIsReturned() {
        brand.setId("1");
        when(retrieveBrandUseCase.getBrandById(brand.getId())).thenReturn(Optional.of(brand));

        BrandDTO foundBrand = brandService.getBrandById(brand.getId()).orElse(null);

        assertNotNull(foundBrand);
        assertEquals(brand.getName(), foundBrand.getName());
        assertEquals(brand.getDescription(), foundBrand.getDescription());

        verify(retrieveBrandUseCase, times(1)).getBrandById(brand.getId());
    }

    @Test
    void findBrandById_whenBrandDoesNotExist_thenBrandIsNotReturned() {
        when(retrieveBrandUseCase.getBrandById("123")).thenReturn(Optional.empty());

        Optional<BrandDTO> foundBrand = brandService.getBrandById("123");
        assertTrue(foundBrand.isEmpty());

        verify(retrieveBrandUseCase, times(1)).getBrandById("123");
    }

    @Test
    void findBrandByName_whenBrandExists_thenBrandIsReturned() {
        when(retrieveBrandUseCase.getBrandByName(any())).thenReturn(Optional.of(brand));

        BrandDTO foundBrand = brandService.getBrandByName("Samsung").orElse(null);

        assertNotNull(foundBrand);
        assertEquals(brand.getName(), foundBrand.getName());
        assertEquals(brand.getDescription(), foundBrand.getDescription());

        verify(retrieveBrandUseCase, times(1)).getBrandByName(any());
    }

    @Test
    void findBrandByName_whenBrandDoesNotExist_thenBrandIsNotReturned() {
        when(retrieveBrandUseCase.getBrandByName("Samsung")).thenReturn(Optional.empty());

        Optional<BrandDTO> foundBrand = brandService.getBrandByName("Samsung");
        assertTrue(foundBrand.isEmpty());

        verify(retrieveBrandUseCase, times(1)).getBrandByName("Samsung");
    }

    @Test
    void getAllBrands_whenBrandsExist_thenBrandsAreReturned() {
        int page = 0, size = 1;
        PaginatedResponse<Brand> brands = new PaginatedResponse<>(List.of(brand), page, size, 1, 1);
        when(retrieveBrandUseCase.getAllBrands(page, size, "ASC")).thenReturn(brands);

        PaginatedResponse<BrandDTO> allBrands = brandService.getAllBrands(page, size, "ASC");

        assertNotNull(allBrands);
        assertEquals(1, allBrands.getItems().size());
        assertEquals(1, allBrands.getTotalItems());
        assertEquals(1, allBrands.getTotalPages());
        assertEquals(page, allBrands.getPage());
        assertEquals(size, allBrands.getSize());

        verify(retrieveBrandUseCase, times(1)).getAllBrands(page, size, "ASC");
    }

}
