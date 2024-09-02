package com.emezon.stock.domain.ports.outbound;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandRepositoryOutPort {

    Brand save(Brand brand);

    Optional<Brand> findById(String id);

    Optional<Brand> findByName(String name);

    PaginatedResponse<Brand> findAll(int page, int size, List<String> sorting);

}
