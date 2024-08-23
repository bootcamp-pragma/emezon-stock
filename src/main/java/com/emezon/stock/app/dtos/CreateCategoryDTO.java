package com.emezon.stock.app.dtos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryDTO {

    @NotNull(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Code is required")
    private String code;

}
