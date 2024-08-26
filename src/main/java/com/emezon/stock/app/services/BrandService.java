package com.emezon.stock.app.services;

import com.emezon.stock.app.dtos.BrandDTO;
import com.emezon.stock.app.dtos.CreateBrandDTO;
import com.emezon.stock.app.mappers.CreateBrandDTOMapper;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.usecases.brand.CreateBrandUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandService {

    private final CreateBrandUseCase createBrandUseCase;

    public BrandDTO createBrand(CreateBrandDTO brand) {
        Brand brandModel = CreateBrandDTOMapper.toModel(brand);
        Brand createdBrand = createBrandUseCase.createBrand(brandModel);
        return CreateBrandDTOMapper.toDTO(createdBrand);
    }

}
