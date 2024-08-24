package com.emezon.stock.domain.exceptions.category;

import com.emezon.stock.domain.common.constants.CategoryErrorMessages;

public class CategoryNameMinLengthException extends RuntimeException {
  public CategoryNameMinLengthException(Integer minLength) {
    super(String.format(CategoryErrorMessages.CATEGORY_NAME_MIN_LENGTH, minLength));
  }
}
