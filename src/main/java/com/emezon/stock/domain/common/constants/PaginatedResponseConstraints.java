package com.emezon.stock.domain.common.constants;

import java.util.List;

public class PaginatedResponseConstraints {

    public static final int PAGE_SIZE_MIN = 1;
    public static final int PAGE_NUMBER_MIN = 0;
    public static final List<String> SORT_DIRECTIONS = List.of("none", "asc", "desc");

    private PaginatedResponseConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
