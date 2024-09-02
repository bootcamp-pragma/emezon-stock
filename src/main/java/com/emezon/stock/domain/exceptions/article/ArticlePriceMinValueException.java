package com.emezon.stock.domain.exceptions.article;

import com.emezon.stock.domain.constants.ArticleErrorMessages;

public class ArticlePriceMinValueException extends RuntimeException {
    public ArticlePriceMinValueException(Integer priceMinValue) {
        super(String.format(ArticleErrorMessages.ARTICLE_PRICE_MIN_VALUE, priceMinValue));
    }
}
