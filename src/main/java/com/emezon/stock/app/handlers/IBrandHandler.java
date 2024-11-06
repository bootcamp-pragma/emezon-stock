package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface IBrandHandler {

    BrandDTO createBrand(CreateBrandDTO brand);

    BrandDTO getBrandById(String id);

    BrandDTO getBrandByName(String name);

    PaginatedResponse<BrandDTO> getAllBrands(MultiValueMap<String, String> queryParams);

}
