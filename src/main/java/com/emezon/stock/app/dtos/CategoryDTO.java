package com.emezon.stock.app.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private String name;
    private String description;
    private String code;

}
