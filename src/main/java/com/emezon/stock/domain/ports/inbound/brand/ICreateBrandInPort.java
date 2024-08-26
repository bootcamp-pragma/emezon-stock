package com.emezon.stock.domain.ports.inbound.brand;

import com.emezon.stock.domain.models.Brand;

public interface ICreateBrandInPort {

    Brand createBrand(Brand brand);

}
