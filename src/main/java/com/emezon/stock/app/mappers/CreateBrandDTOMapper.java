package com.emezon.stock.app.mappers;

import com.emezon.stock.app.dtos.BrandDTO;
import com.emezon.stock.app.dtos.CreateBrandDTO;
import com.emezon.stock.domain.models.Brand;

import java.util.List;

public class CreateBrandDTOMapper {

    private CreateBrandDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Brand toModel(CreateBrandDTO brand) {
        Brand brandModel = new Brand();
        brandModel.setName(brand.getName());
        brandModel.setDescription(brand.getDescription());
        return brandModel;
    }

    public static BrandDTO toDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setDescription(brand.getDescription());
        return brandDTO;
    }

    public static List<BrandDTO> toDTOList(List<Brand> brands) {
        return brands.stream()
                .map(CreateBrandDTOMapper::toDTO)
                .toList();
    }

}
