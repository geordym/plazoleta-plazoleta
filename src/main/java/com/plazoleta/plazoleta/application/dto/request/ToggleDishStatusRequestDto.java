package com.plazoleta.plazoleta.application.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ToggleDishStatusRequestDto {
    @NotNull(message = "Dish ID is required.")
    private Long dishId;

    @NotNull(message = "Active status is required.")
    private Boolean active;
}
