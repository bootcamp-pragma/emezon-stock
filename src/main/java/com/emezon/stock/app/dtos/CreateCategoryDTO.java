package com.emezon.stock.app.dtos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryDTO {

    private String name;

    private String description;

    private String code;

}
