package com.emezon.stock.domain.constants;

import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageNumberInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponsePageSizeInvalidException;
import com.emezon.stock.domain.exceptions.paginatedresponse.PaginatedResponseSortDirectionInvalidException;

import java.util.List;

public class PaginatedResponseConstraints {

    public static final int PAGE_SIZE_MIN = 1;
    public static final int PAGE_NUMBER_MIN = 0;
    public static final List<String> SORT_DIRECTIONS = List.of("asc", "desc");
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String VALID_SORT_FORMAT = "^((?!.*\\.\\..*)[a-zA-Z]+(\\.[a-zA-Z]{1,20}){0,3}),(asc|desc)$";

    public static void validateParameters(int page, int size, List<String> sorting) {
        if (page < PAGE_NUMBER_MIN) {
            throw new PaginatedResponsePageNumberInvalidException();
        }
        if (size < PAGE_SIZE_MIN) {
            throw new PaginatedResponsePageSizeInvalidException();
        }
        for (String sort : sorting) {
            if (!sort.matches(VALID_SORT_FORMAT)) {
                throw new PaginatedResponseSortDirectionInvalidException();
            }
        }
    }

    private PaginatedResponseConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
