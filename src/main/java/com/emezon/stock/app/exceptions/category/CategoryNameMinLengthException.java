package com.emezon.stock.app.exceptions.category;

import com.emezon.stock.app.errorhandling.messages.CategoryErrorMessages;

public class CategoryNameMinLengthException extends RuntimeException {
  public CategoryNameMinLengthException(Integer minLength) {
    super(String.format(CategoryErrorMessages.CATEGORY_NAME_MIN_LENGTH, minLength));
  }
}
