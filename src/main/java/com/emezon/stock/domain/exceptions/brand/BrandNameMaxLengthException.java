package com.emezon.stock.domain.exceptions.brand;

import com.emezon.stock.domain.constants.BrandErrorMessages;

public class BrandNameMaxLengthException extends RuntimeException {
    public BrandNameMaxLengthException(Integer maxLength) {
        super(String.format(BrandErrorMessages.BRAND_NAME_MAX_LENGTH, maxLength));
    }
}
