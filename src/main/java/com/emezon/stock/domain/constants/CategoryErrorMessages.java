package com.emezon.stock.domain.constants;

public class CategoryErrorMessages {
    public static final String CATEGORY_ALREADY_EXISTS = "Category with name '%s' already exists";
    public static final String CATEGORY_ALREADY_EXISTS_DETAILS = "Category name must be unique. Please try again with a different name.";
    public static final String CATEGORY_NAME_REQUIRED = "Category name is required";
    public static final String CATEGORY_DESCRIPTION_REQUIRED = "Category description is required";
    public static final String CATEGORY_NAME_TOO_LONG = "Category name must not exceed %d characters";
    public static final String CATEGORY_DESCRIPTION_TOO_LONG = "Category description must not exceed %d characters";
    public static final String CATEGORY_NOT_FOUND_BY_ID = "Category with id '%s' not found";

    private CategoryErrorMessages() {
        throw new IllegalStateException("Utility class");
    }

}
