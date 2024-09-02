package com.emezon.stock.domain.utils.validators;

import com.emezon.stock.domain.utils.annotations.ValidPageableRequest;
import com.emezon.stock.domain.constants.PaginatedResponseConstraints;
import com.emezon.stock.domain.constants.PaginatedResponseErrorMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.MultiValueMap;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class PageableRequestValidator implements ConstraintValidator<ValidPageableRequest, MultiValueMap<String, String>> {

    private Set<String> allowedParams;
    private Set<String> allowedSortFormats;

    @Override
    public void initialize(ValidPageableRequest constraintAnnotation) {
        allowedParams = new HashSet<>(List.of(constraintAnnotation.allowedParams()));
        allowedSortFormats = new HashSet<>(List.of(constraintAnnotation.allowedSortFormats()));
    }

    @Override
    public boolean isValid(MultiValueMap<String, String> s, ConstraintValidatorContext context) {
        boolean error = false;
        context.disableDefaultConstraintViolation();
        for (String key : s.keySet()) {
            if (!allowedParams.contains(key)) {
                context.buildConstraintViolationWithTemplate("Unknown parameter: " + key).addConstraintViolation();
                error = true;
            }
            if (key.equals("sort")) {
                for (String value : s.get(key)) {
                    boolean valid = false;
                    for (String format : allowedSortFormats) {
                        if (value.matches(format)) {
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        error = true;
                        context.buildConstraintViolationWithTemplate(PaginatedResponseErrorMessages.SORT_PARAMETER_INVALID)
                                .addConstraintViolation();
                    }
                }
            } else {
                if (s.get(key).size() != 1) {
                    context.buildConstraintViolationWithTemplate("Parameter " + key + " must have exactly one value").addConstraintViolation();
                    error = true;
                }
                if (key.equals("page")) {
                    try {
                        int page = Integer.parseInt(Objects.requireNonNull(s.getFirst(key)));
                        if (page < PaginatedResponseConstraints.PAGE_NUMBER_MIN) {
                            context.buildConstraintViolationWithTemplate(PaginatedResponseErrorMessages.PAGE_NUMBER_INVALID)
                                    .addConstraintViolation();
                            error = true;
                        }
                    } catch (NumberFormatException e) {
                        context.buildConstraintViolationWithTemplate("Page number must be an integer")
                                .addConstraintViolation();
                        error = true;
                    }
                } else if (key.equals("size")) {
                    try {
                        int size = Integer.parseInt(Objects.requireNonNull(s.getFirst(key)));
                        if (size < PaginatedResponseConstraints.PAGE_SIZE_MIN) {
                            context.buildConstraintViolationWithTemplate(PaginatedResponseErrorMessages.PAGE_SIZE_INVALID)
                                    .addConstraintViolation();
                            error = true;
                        }
                    } catch (NumberFormatException e) {
                        context.buildConstraintViolationWithTemplate("Page size must be an integer")
                                .addConstraintViolation();
                        error = true;
                    }
                }
            }
        }

        return !error;
    }
}
