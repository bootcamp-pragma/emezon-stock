package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.domain.models.Brand;

import java.util.List;

public class BrandDTOMapper {

    private BrandDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Brand toModel(CreateBrandDTO brand) {
        Brand brandModel = new Brand();
        brandModel.setName(brand.getName());
        brandModel.setDescription(brand.getDescription());
        return brandModel;
    }

    public static Brand toModel(BrandDTO brandDTO) {
        Brand brandModel = new Brand();
        brandModel.setId(brandDTO.getId());
        brandModel.setName(brandDTO.getName());
        brandModel.setDescription(brandDTO.getDescription());
        return brandModel;
    }

    public static BrandDTO toDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setDescription(brand.getDescription());
        return brandDTO;
    }

}
