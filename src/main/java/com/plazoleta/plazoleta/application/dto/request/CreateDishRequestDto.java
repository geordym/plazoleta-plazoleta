package com.plazoleta.plazoleta.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDishRequestDto {
    @NotBlank(message = "The name of the dish is required.")
    private String name;

    @Positive(message = "The price must be a positive integer.")
    private int price;

    @NotBlank(message = "The description is required.")
    private String description;

    @NotBlank(message = "The image URL is required.")
    @URL(message = "The image URL must be a valid URL.")
    private String imageUrl;

    @NotBlank(message = "The category is required.")
    private String category;

    @NotNull(message = "The restaurant ID is required.")
    private Long restaurantId;

}
