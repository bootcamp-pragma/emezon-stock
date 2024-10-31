package com.emezon.stock.domain.usecases.brand;

import com.emezon.stock.domain.constants.BrandConstraints;
import com.emezon.stock.domain.exceptions.brand.*;
import com.emezon.stock.domain.models.Brand;
import com.emezon.stock.domain.api.brand.ICreateBrandInPort;
import com.emezon.stock.domain.spi.IBrandRepositoryOutPort;

import java.util.Optional;


public class CreateBrandUseCase implements ICreateBrandInPort {

    private final IBrandRepositoryOutPort brandRepositoryOutPort;

    public CreateBrandUseCase(IBrandRepositoryOutPort brandRepositoryOutPort) {
        this.brandRepositoryOutPort = brandRepositoryOutPort;
    }

    @Override
    public Brand createBrand(Brand brand) {
        Brand processedBrand = processAndValidateBrand(brand);
        Optional<Brand> brandByName = brandRepositoryOutPort.findByName(processedBrand.getName());
        if (brandByName.isPresent()) {
            throw new BrandNameAlreadyExistsException(processedBrand.getName());
        }
        return brandRepositoryOutPort.save(processedBrand);
    }

    private Brand processAndValidateBrand(Brand brand) {
        if (brand.getName() == null) throw new BrandNameRequiredException();
        brand.setName(brand.getName().trim());
        if (brand.getName().length() > BrandConstraints.NAME_MAX_LENGTH) {
            throw new BrandNameMaxLengthException(BrandConstraints.NAME_MAX_LENGTH);
        }
        if (brand.getDescription() == null) throw new BrandDescriptionRequiredException();
        brand.setDescription(brand.getDescription().trim());
        if (brand.getDescription().length() > BrandConstraints.DESCRIPTION_MAX_LENGTH) {
            throw new BrandDescriptionMaxLengthException(BrandConstraints.DESCRIPTION_MAX_LENGTH);
        }
        return brand;
    }

}
