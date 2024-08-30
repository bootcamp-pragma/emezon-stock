package com.emezon.stock.domain.exceptions.paginatedresponse;

import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;

public class PaginatedResponsePageSizeInvalidException extends RuntimeException {
    public PaginatedResponsePageSizeInvalidException() {
        super(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID);
    }
}
