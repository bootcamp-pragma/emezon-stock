package com.emezon.stock.domain.ports.inbound.brand;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;

import java.util.Optional;

public interface IRetrieveBrandInPort {

    Optional<Brand> getBrandById(String id);

    Optional<Brand> getBrandByName(String name);

    PaginatedResponse<Brand> getAllBrands(int page, int size, String sortDirection);

}
