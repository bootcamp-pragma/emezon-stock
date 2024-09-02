package com.emezon.stock.domain.exceptions.article;

import com.emezon.stock.domain.constants.ArticleErrorMessages;

public class ArticleStockMinValueException extends RuntimeException {
    public ArticleStockMinValueException(int stockMinValue) {
        super(String.format(ArticleErrorMessages.ARTICLE_STOCK_MIN_VALUE, stockMinValue));
    }
}
