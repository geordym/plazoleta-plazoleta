package com.plazoleta.plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderRequestDto {
    private Long id;
    private Long restaurantId;
    private LocalDateTime orderDate;
    private List<OrderItemRequestDto> orderItems;
}
