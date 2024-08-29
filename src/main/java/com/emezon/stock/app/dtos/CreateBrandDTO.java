package com.emezon.stock.app.dtos;

import com.emezon.stock.domain.common.constants.BrandErrorMessages;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBrandDTO {

    @NotBlank(message = BrandErrorMessages.BRAND_NAME_REQUIRED)
    private String name;

    @NotBlank(message = BrandErrorMessages.BRAND_DESCRIPTION_REQUIRED)
    private String description;

    public CreateBrandDTO(String name, String description) {
        this.name = name;
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
    }

}
