package com.emezon.stock.domain.constants;

public class ArticleConstraints {

    public static final int PRICE_MIN_VALUE = 0;
    public static final int STOCK_MIN_VALUE = 0;
    public static final int MIN_NUMBER_OF_CATEGORIES = 1;
    public static final int MAX_NUMBER_OF_CATEGORIES = 3;

    public static final String RESTOCK_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    private ArticleConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
