package com.emezon.stock.domain.api.brand;

import com.emezon.stock.domain.models.Brand;

public interface IPersistBrandInPort {

    Brand createBrand(Brand brand);

}
