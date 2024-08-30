package com.emezon.stock.domain.constants;

public class CategoryConstraints {

    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;

    private CategoryConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
