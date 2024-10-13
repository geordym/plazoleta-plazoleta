package com.plazoleta.plazoleta.application.dto.response;

import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemResponseDto {
    private Long orderItemId;
    private Order order;
    private Dish dish;
    private Integer quantity;
}
