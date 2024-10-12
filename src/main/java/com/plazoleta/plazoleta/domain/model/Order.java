package com.plazoleta.plazoleta.domain.model;


import com.plazoleta.plazoleta.domain.enums.OrderStatus;
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
public class Order {
    private Long id;
    private Restaurant restaurant;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Long customerId;
    private List<OrderItem> orderItems;
}
