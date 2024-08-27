package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.BrandDTO;
import com.emezon.stock.app.dtos.CreateBrandDTO;
import com.emezon.stock.app.mappers.CreateBrandDTOMapper;
import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
import com.emezon.stock.domain.usecases.brand.RetrieveBrandUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BrandService {

    private final CreateBrandUseCase createBrandUseCase;
    private final RetrieveBrandUseCase retrieveBrandUseCase;

    public BrandDTO createBrand(CreateBrandDTO brand) {
        Brand brandModel = CreateBrandDTOMapper.toModel(brand);
        Brand createdBrand = createBrandUseCase.createBrand(brandModel);
        return CreateBrandDTOMapper.toDTO(createdBrand);
    }

    public Optional<BrandDTO> getBrandById(String id) {
        Optional<Brand> brand = retrieveBrandUseCase.getBrandById(id);
        return brand.map(CreateBrandDTOMapper::toDTO);
    }

    public Optional<BrandDTO> getBrandByName(String name) {
        Optional<Brand> brand = retrieveBrandUseCase.getBrandByName(name);
        return brand.map(CreateBrandDTOMapper::toDTO);
    }

    public PaginatedResponse<BrandDTO> getAllBrands(int page, int size, String sortDirection) {
        PaginatedResponse<Brand> brands = retrieveBrandUseCase.getAllBrands(page, size, sortDirection);
        return new PaginatedResponse<>(
                CreateBrandDTOMapper.toDTOList(brands.getItems()),
                brands.getPage(),
                brands.getSize(),
                brands.getTotalItems(),
                brands.getTotalPages()
        );
    }

}
