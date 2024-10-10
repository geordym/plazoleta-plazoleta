package com.plazoleta.plazoleta.application.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateDishRequestDto {
    private Long dishId;
    private Integer price;
    private String description;
}
