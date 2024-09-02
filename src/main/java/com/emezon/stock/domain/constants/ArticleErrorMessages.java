package com.emezon.stock.domain.constants;

public class ArticleErrorMessages {

    public static final String ARTICLE_PRICE_MIN_VALUE = "The price of the article must be greater than or equal to %d";
    public static final String ARTICLE_STOCK_MIN_VALUE = "The stock of the article must be greater than or equal to %d";
    public static final String ARTICLE_INVALID_NUMBER_OF_CATEGORIES = "The number of categories must be between 1 and 3";
    public static final String ARTICLE_DUPLICATED_CATEGORIES = "Duplicated categories. The categories must be unique.";
    public static final String ARTICLE_BRAND_ID_NOT_VALID = "The brand id most be a valid UUID";
    public static final String ARTICLE_BRAND_ID_REQUIRED = "The brand id is required";
    public static final String ARTICLE_NAME_REQUIRED = "The name of the article is required";
    public static final String ARTICLE_DESCRIPTION_REQUIRED = "The description of the article is required";

    private ArticleErrorMessages() {
        throw new IllegalStateException("Utility class");
    }

}
