package com.emezon.stock.domain.exceptions.brand;

import com.emezon.stock.domain.constants.BrandErrorMessages;

public class BrandDescriptionMaxLengthException extends RuntimeException {
    public BrandDescriptionMaxLengthException(Integer maxLength) {
        super(String.format(BrandErrorMessages.BRAND_DESCRIPTION_TOO_LONG, maxLength));
    }
}
