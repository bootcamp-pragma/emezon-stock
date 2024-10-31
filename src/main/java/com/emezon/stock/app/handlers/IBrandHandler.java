package com.emezon.stock.app.handlers;

import com.emezon.stock.app.dtos.brand.BrandDTO;
import com.emezon.stock.app.dtos.brand.CreateBrandDTO;
import com.emezon.stock.domain.utils.PaginatedResponse;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

public interface IBrandHandler {

    public BrandDTO createBrand(CreateBrandDTO brand);

    public Optional<BrandDTO> getBrandById(String id);

    public Optional<BrandDTO> getBrandByName(String name);

    public PaginatedResponse<BrandDTO> getAllBrands(MultiValueMap<String, String> queryParams);

}
