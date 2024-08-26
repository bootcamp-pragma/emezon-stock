package com.emezon.stock.domain.usecases.brand;

import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.ports.inbound.brand.ICreateBrandInPort;
import com.emezon.stock.domain.ports.outbound.IBrandRepositoryOutPort;


public class CreateBrandUseCase implements ICreateBrandInPort {

    private final IBrandRepositoryOutPort brandRepositoryOutPort;

    public CreateBrandUseCase(IBrandRepositoryOutPort brandRepositoryOutPort) {
        this.brandRepositoryOutPort = brandRepositoryOutPort;
    }

    @Override
    public Brand createBrand(Brand brand) {
        return null;
    }

}
