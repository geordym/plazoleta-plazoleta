package com.plazoleta.plazoleta.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    private Long orderItemId;
    private Order order;
    private Dish dish;
    private Integer quantity;
}
