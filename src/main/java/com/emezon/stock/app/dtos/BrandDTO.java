package com.emezon.stock.app.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandDTO {

    private String id;
    private String name;
    private String description;

    public BrandDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
