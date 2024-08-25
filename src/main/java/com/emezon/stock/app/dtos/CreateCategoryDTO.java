package com.emezon.stock.app.dtos;
import com.emezon.stock.domain.common.constants.CategoryErrorMessages;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
public class CreateCategoryDTO {

    @NotBlank(message = CategoryErrorMessages.CATEGORY_NAME_REQUIRED)
    private String name;

    @NotBlank(message = CategoryErrorMessages.CATEGORY_DESCRIPTION_REQUIRED)
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
