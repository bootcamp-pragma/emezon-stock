package com.emezon.stock.app.dtos.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddSupplyFromMSTransaction {

    @NotNull(message = "The payload is required")
    @NotBlank(message = "The payload cannot be empty")
    private String payload;

}
