package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.exceptions.brand.*;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateBrandUseCaseTests {

    @Mock
    private IBrandRepositoryOutPort brandRepositoryOutPort;

    @InjectMocks
    private CreateBrandUseCase createBrandUseCase;

    private Brand brand;

    @BeforeEach
    public void setUp() {
        brand = new Brand("SAMS", "Samsung Electronics");
    }

    @Test
    void createBrand_whenBrandNameIsNull_thenBrandNameRequiredExceptionIsThrown() {
        brand.setName(null);

        assertThrows(BrandNameRequiredException.class, () -> createBrandUseCase.createBrand(brand));

        verify(brandRepositoryOutPort, never()).save(any());
    }

    @Test
    void createBrand_whenBrandDescriptionIsNull_thenBrandDescriptionRequiredExceptionIsThrown() {
        brand.setDescription(null);

        assertThrows(BrandDescriptionRequiredException.class, () -> createBrandUseCase.createBrand(brand));

        verify(brandRepositoryOutPort, never()).save(any());
    }

    @Test
    void createBrand_whenBrandNameIsTooLong_thenBrandNameMaxLengthExceptionIsThrown() {
        brand.setName("This is a very long brand name that exceeds the maximum length allowed for a brand name");

        assertThrows(BrandNameMaxLengthException.class, () -> createBrandUseCase.createBrand(brand));

        verify(brandRepositoryOutPort, never()).save(any());
    }

    @Test
    void createBrand_whenBrandDescriptionIsTooLong_thenBrandDescriptionMaxLengthExceptionIsThrown() {
        brand.setDescription("This is a very long brand description that exceeds the maximum length allowed for a brand description" +
                "This is a very long brand description that exceeds the maximum length allowed for a brand description");

        assertThrows(BrandDescriptionMaxLengthException.class, () -> createBrandUseCase.createBrand(brand));

        verify(brandRepositoryOutPort, never()).save(any());
    }

    @Test
    void createBrand_whenBrandNameAlreadyExists_thenBrandNameAlreadyExistsExceptionIsThrown() {
        when(brandRepositoryOutPort.findByName(brand.getName())).thenReturn(Optional.of(brand));

        assertThrows(BrandNameAlreadyExistsException.class, () -> createBrandUseCase.createBrand(brand));

        verify(brandRepositoryOutPort, times(1)).findByName(brand.getName());
        verify(brandRepositoryOutPort, never()).save(any());
    }

    @Test
    void createBrand_whenBrandIsValid_thenBrandIsCreated() {
        when(brandRepositoryOutPort.findByName(brand.getName())).thenReturn(Optional.empty());
        when(brandRepositoryOutPort.save(brand)).thenReturn(brand);

        Brand createdBrand = createBrandUseCase.createBrand(brand);
        assertNotNull(createdBrand);
        assertEquals(brand.getName(), createdBrand.getName());

        verify(brandRepositoryOutPort, times(1)).findByName(brand.getName());
        verify(brandRepositoryOutPort, times(1)).save(brand);
    }


}
