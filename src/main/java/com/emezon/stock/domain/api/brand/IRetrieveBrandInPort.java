package com.emezon.stock.domain.api.brand;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Brand;

import java.util.Optional;

public interface IRetrieveBrandInPort {

    Optional<Brand> getBrandById(String id);

    Optional<Brand> getBrandByName(String name);

    PaginatedResponse<Brand> getAllBrands(PaginatedResponseParams params);

}
