package com.emezon.stock.domain.constants;

public class ArticleConstraints {

    public static final int PRICE_MIN_VALUE = 0;
    public static final int STOCK_MIN_VALUE = 0;
    public static final int MIN_NUMBER_OF_CATEGORIES = 1;
    public static final int MAX_NUMBER_OF_CATEGORIES = 3;

    private ArticleConstraints() {
        throw new IllegalStateException("Utility class");
    }

}
