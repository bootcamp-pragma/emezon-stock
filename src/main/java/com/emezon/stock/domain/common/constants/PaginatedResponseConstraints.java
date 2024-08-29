package com.emezon.stock.domain.common.constants;

import java.util.List;

public class PaginatedResponseConstraints {

    public static final int PAGE_SIZE_MIN = 1;
    public static final int PAGE_NUMBER_MIN = 0;
    public static final List<String> SORT_DIRECTIONS = List.of("asc", "desc");
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String VALID_SORT_FORMAT = "^[a-zA-Z]+,(asc|desc)$";

    private PaginatedResponseConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
