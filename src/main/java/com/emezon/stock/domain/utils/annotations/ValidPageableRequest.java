package com.emezon.stock.domain.utils.annotations;

import com.emezon.stock.domain.utils.validators.PageableRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PageableRequestValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPageableRequest {
    String message() default "Some of the values of the sort parameter do not comply with the format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] allowedParams() default  {"page", "size", "sort"};
    String[] allowedSortFormats() default {"^((?!.*\\.\\..*)[a-zA-Z]+(\\.[a-zA-Z]+)*),(asc|desc)$"};
}
