package com.plazoleta.plazoleta.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dish {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String imageUrl;
    private String category;
    private Long restaurantId;
    private boolean active = true;

    public Dish(Long id) {
        this.id = id;
    }

    public Dish(String name, int price, String description, String imageUrl, String category, Long restaurantId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.restaurantId = restaurantId;
    }
}
