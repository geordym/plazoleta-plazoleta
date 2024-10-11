package com.plazoleta.plazoleta.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishResponseDto {
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
    private boolean active = true;
}
