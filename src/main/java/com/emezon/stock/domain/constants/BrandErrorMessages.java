package com.emezon.stock.domain.constants;

public class BrandErrorMessages {

    public static final String BRAND_NAME_ALREADY_EXISTS = "Brand with name '%s' already exists";
    public static final String BRAND_NAME_ALREADY_EXISTS_DETAILS = "Brand name must be unique. Please try again with a different name.";
    public static final String BRAND_NAME_REQUIRED = "Brand name is required";
    public static final String BRAND_NAME_TOO_LONG = "Brand name must not exceed %d characters";
    public static final String BRAND_DESCRIPTION_REQUIRED = "Brand description is required";
    public static final String BRAND_DESCRIPTION_TOO_LONG = "Brand description must not exceed %d characters";
    public static final String BRAND_NOT_FOUND_BY_ID = "Brand with id '%s' not found";

    private BrandErrorMessages() {
        throw new IllegalStateException("Utility class");
    }

}
