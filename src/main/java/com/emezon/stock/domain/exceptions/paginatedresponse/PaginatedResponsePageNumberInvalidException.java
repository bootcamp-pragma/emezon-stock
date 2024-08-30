package com.emezon.stock.domain.exceptions.paginatedresponse;

import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;

public class PaginatedResponsePageNumberInvalidException extends RuntimeException {
    public PaginatedResponsePageNumberInvalidException() {
        super(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID);
    }
}
