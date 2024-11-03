package com.emezon.stock.domain.spi;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.models.Brand;

import java.util.Optional;

public interface IBrandRepositoryOutPort {

    Brand save(Brand brand);

    Optional<Brand> findById(String id);

    Optional<Brand> findByName(String name);

    PaginatedResponse<Brand> findAll(PaginatedResponseParams params);

}
