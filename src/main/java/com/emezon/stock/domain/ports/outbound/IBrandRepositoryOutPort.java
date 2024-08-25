package com.emezon.stock.domain.ports.outbound;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.models.Brand;

import java.util.Optional;

public interface IBrandRepositoryOutPort {

    Brand save(Brand brand);

    Optional<Brand> findById(String id);

    Optional<Brand> findByName(String name);

}
