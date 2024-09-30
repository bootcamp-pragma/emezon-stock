package com.emezon.stock.app.dtos.category;

import com.emezon.stock.domain.constants.CategoryConstraints;
import com.emezon.stock.domain.constants.CategoryErrorMessages;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryDTO {

    @NotBlank(message = CategoryErrorMessages.CATEGORY_NAME_REQUIRED)
    @Length(max = CategoryConstraints.NAME_MAX_LENGTH, message = CategoryErrorMessages.CATEGORY_NAME_TOO_LONG)
    private String name;

    @NotBlank(message = CategoryErrorMessages.CATEGORY_DESCRIPTION_REQUIRED)
    @Length(max = CategoryConstraints.DESCRIPTION_MAX_LENGTH, message = CategoryErrorMessages.CATEGORY_DESCRIPTION_TOO_LONG)
    private String description;

    @NotBlank(message = CategoryErrorMessages.CATEGORY_CODE_REQUIRED)
    private String code;

    public CreateCategoryDTO(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    @PostConstruct
    public void init() {
        if (this.name != null) {
            this.name = this.name.trim();
        }
        if (this.description != null) {
            this.description = this.description.trim();
        }
        if (this.code != null) {
            this.code = this.code.trim();
        }
    }

}
