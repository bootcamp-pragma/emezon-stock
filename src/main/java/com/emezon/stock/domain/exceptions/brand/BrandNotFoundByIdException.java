package com.emezon.stock.domain.exceptions.brand;

import com.emezon.stock.domain.constants.BrandErrorMessages;

public class BrandNotFoundByIdException extends RuntimeException {
    public BrandNotFoundByIdException(String id) {
        super(String.format(BrandErrorMessages.BRAND_NOT_FOUND_BY_ID, id));
    }
}
