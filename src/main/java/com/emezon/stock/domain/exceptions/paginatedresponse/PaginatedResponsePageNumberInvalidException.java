package com.emezon.stock.domain.exceptions.paginatedresponse;

import com.emezon.stock.domain.common.constants.PaginatedResponseErrorMessages;

public class PaginatedResponsePageNumberInvalidException extends RuntimeException {
    public PaginatedResponsePageNumberInvalidException() {
        super(PaginatedResponseErrorMessages.CATEGORY_PAGE_NUMBER_INVALID);
    }
}
