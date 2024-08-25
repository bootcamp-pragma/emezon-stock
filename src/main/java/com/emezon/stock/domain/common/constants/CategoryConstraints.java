package com.emezon.stock.domain.common.constants;

import java.util.List;

public class CategoryConstraints {

    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int PAGE_SIZE_MIN = 1;
    public static final int PAGE_NUMBER_MIN = 0;
    public static final List<String> SORT_DIRECTIONS = List.of("none", "asc", "desc");

    private CategoryConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
