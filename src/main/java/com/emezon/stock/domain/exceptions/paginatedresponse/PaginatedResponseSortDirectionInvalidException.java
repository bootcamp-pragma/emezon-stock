package com.emezon.stock.domain.exceptions.paginatedresponse;

import com.emezon.stock.domain.common.constants.PaginatedResponseErrorMessages;

public class PaginatedResponseSortDirectionInvalidException extends RuntimeException {

    public PaginatedResponseSortDirectionInvalidException() {
        super(PaginatedResponseErrorMessages.SORT_DIRECTION_INVALID);
    }

}
