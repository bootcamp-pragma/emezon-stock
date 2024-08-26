package com.emezon.stock.domain.exceptions.brand;

import com.emezon.stock.domain.common.constants.BrandErrorMessages;

public class BrandNameAlreadyExistsException extends RuntimeException {
    public BrandNameAlreadyExistsException(String name) {
        super(String.format(BrandErrorMessages.BRAND_NAME_ALREADY_EXISTS, name));
    }
}
