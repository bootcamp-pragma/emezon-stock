package com.emezon.stock.domain.exceptions.article;

import com.emezon.stock.domain.constants.ArticleErrorMessages;

public class ArticleCategoriesNumberInvalidException extends RuntimeException {

    public ArticleCategoriesNumberInvalidException(int minCategories, int maxCategories) {
        super(String.format(ArticleErrorMessages.ARTICLE_INVALID_NUMBER_OF_CATEGORIES, minCategories, maxCategories));
    }
}
