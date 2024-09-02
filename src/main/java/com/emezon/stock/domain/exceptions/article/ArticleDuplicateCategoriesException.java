package com.emezon.stock.domain.exceptions.article;

import com.emezon.stock.domain.constants.ArticleErrorMessages;

public class ArticleDuplicateCategoriesException extends RuntimeException {

    public ArticleDuplicateCategoriesException() {
        super(ArticleErrorMessages.ARTICLE_DUPLICATED_CATEGORIES);
    }
}
