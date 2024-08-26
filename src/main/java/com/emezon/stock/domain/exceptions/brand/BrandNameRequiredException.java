package com.emezon.stock.domain.exceptions.brand;

import com.emezon.stock.domain.common.constants.BrandErrorMessages;

public class BrandNameRequiredException extends RuntimeException {
    public BrandNameRequiredException() {
        super(BrandErrorMessages.BRAND_NAME_REQUIRED);
    }
}
