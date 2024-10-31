package com.emezon.stock.domain.usecases.brand;

import com.emezon.stock.domain.utils.PaginatedResponse;
import com.emezon.stock.domain.utils.PaginatedResponseParams;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.api.brand.IRetrieveBrandInPort;
import com.emezon.stock.domain.spi.IBrandRepositoryOutPort;

import java.util.Optional;

public class RetrieveBrandUseCase implements IRetrieveBrandInPort {

    private final IBrandRepositoryOutPort brandRepositoryOutPort;

    public RetrieveBrandUseCase(IBrandRepositoryOutPort brandRepositoryOutPort) {
        this.brandRepositoryOutPort = brandRepositoryOutPort;
    }

    @Override
    public Optional<Brand> getBrandById(String id) {
        return brandRepositoryOutPort.findById(id);
    }

    @Override
    public Optional<Brand> getBrandByName(String name) {
        return brandRepositoryOutPort.findByName(name);
    }

    @Override
    public PaginatedResponse<Brand> getAllBrands(PaginatedResponseParams params) {
        PaginatedResponseConstraints.validateParameters(params);
        return brandRepositoryOutPort.findAll(params);
    }
}
