package com.emezon.stock.domain.usecases.brand;

import com.emezon.stock.domain.common.classes.PaginatedResponse;
import com.emezon.stock.domain.common.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;
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
    public PaginatedResponse<Brand> getAllBrands(int page, int size, String sortDirection) {
        sortDirection = sortDirection.trim().toLowerCase();
        validateParameters(page, size, sortDirection);
        return brandRepositoryOutPort.findAll(page, size, sortDirection);
    }

    private void validateParameters(int page, int size, String sortDirection) {
        if (page < PaginatedResponseConstraints.PAGE_NUMBER_MIN) {
            throw new PaginatedResponsePageNumberInvalidException();
        }
        if (size < PaginatedResponseConstraints.PAGE_SIZE_MIN) {
            throw new PaginatedResponsePageSizeInvalidException();
        }
        if (!PaginatedResponseConstraints.SORT_DIRECTIONS.contains(sortDirection)) {
            throw new PaginatedResponseSortDirectionInvalidException();
        }
    }
}
