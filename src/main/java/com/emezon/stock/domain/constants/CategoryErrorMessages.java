package com.emezon.stock.domain.constants;

public class CategoryErrorMessages {
    public static final String CATEGORY_ALREADY_EXISTS = "Category with name '%s' already exists";
    public static final String CATEGORY_ALREADY_EXISTS_DETAILS = "Category name must be unique. Please try again with a different name.";
    public static final String CATEGORY_CODE_ALREADY_EXISTS = "Category with code '%s' already exists";
    public static final String CATEGORY_CODE_ALREADY_EXISTS_DETAILS = "Category code must be unique. Please try again with a different code.";
    public static final String CATEGORY_NAME_REQUIRED = "Category name is required";
    public static final String CATEGORY_CODE_REQUIRED = "Category code is required";
    public static final String CATEGORY_DESCRIPTION_REQUIRED = "Category description is required";
    public static final String CATEGORY_NAME_MAX_LENGTH = "Category name must not exceed %d characters";
    public static final String CATEGORY_DESCRIPTION_MAX_LENGTH = "Category description must not exceed %d characters";

    private CategoryErrorMessages() {
        throw new IllegalStateException("Utility class");
    }

}
