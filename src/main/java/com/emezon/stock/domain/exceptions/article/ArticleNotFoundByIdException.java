package com.emezon.stock.domain.exceptions.article;

import com.emezon.stock.domain.constants.ArticleErrorMessages;

public class ArticleNotFoundByIdException extends RuntimeException {
  public ArticleNotFoundByIdException(String id) {
    super(String.format(ArticleErrorMessages.ARTICLE_NOT_FOUND_BY_ID, id));
  }
}
