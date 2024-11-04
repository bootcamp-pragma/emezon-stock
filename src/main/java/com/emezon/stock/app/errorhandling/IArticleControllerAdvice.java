package com.emezon.stock.app.errorhandling;

import com.emezon.stock.domain.exceptions.article.*;

public interface IArticleControllerAdvice<T> {

    Object handleArticleCategoriesNumberInvalidException(ArticleCategoriesNumberInvalidException ex, T request);

    Object handleArticleDuplicateCategoriesException(ArticleDuplicateCategoriesException ex, T request);

    Object handleArticlePriceMinValueException(ArticlePriceMinValueException ex, T request);

    Object handleArticleStockMinValueException(ArticleStockMinValueException ex, T request);

    Object handleArticleNotFoundByIdException(ArticleNotFoundByIdException ex, T request);

}
