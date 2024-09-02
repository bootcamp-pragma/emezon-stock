package com.emezon.stock.domain.exceptions.brand;

import com.emezon.stock.domain.constants.BrandErrorMessages;

public class BrandDescriptionRequiredException extends RuntimeException {
    public BrandDescriptionRequiredException() {
        super(BrandErrorMessages.BRAND_DESCRIPTION_REQUIRED);
    }
}
