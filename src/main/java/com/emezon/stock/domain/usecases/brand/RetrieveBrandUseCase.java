package com.emezon.stock.domain.usecases.brand;

import com.emezon.stock.domain.common.PaginatedResponse;
import com.emezon.stock.domain.common.PaginatedResponseParams;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.ports.inbound.brand.IRetrieveBrandInPort;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;

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
