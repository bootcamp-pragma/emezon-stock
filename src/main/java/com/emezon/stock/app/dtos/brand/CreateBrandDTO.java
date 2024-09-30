package com.emezon.stock.app.dtos.brand;

import com.emezon.stock.domain.constants.BrandConstraints;
import com.emezon.stock.domain.constants.BrandErrorMessages;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CreateBrandDTO {

    @NotBlank(message = BrandErrorMessages.BRAND_NAME_REQUIRED)
    @Length(max = BrandConstraints.NAME_MAX_LENGTH, message = BrandErrorMessages.BRAND_NAME_TOO_LONG)
    private String name;

    @NotBlank(message = BrandErrorMessages.BRAND_DESCRIPTION_REQUIRED)
    @Length(max = BrandConstraints.DESCRIPTION_MAX_LENGTH, message = BrandErrorMessages.BRAND_DESCRIPTION_TOO_LONG)
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
