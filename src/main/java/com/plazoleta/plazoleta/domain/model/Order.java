package com.plazoleta.plazoleta.domain.model;


import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.external.Employee;
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

    private Integer reclaimCode;

    private Long employeeAssignedId;

    public Order(Long id, Restaurant restaurant, LocalDateTime orderDate, OrderStatus status, Long customerId, List<OrderItem> orderItems, Long employeeAssignedId) {
        this.id = id;
        this.restaurant = restaurant;
        this.orderDate = orderDate;
        this.status = status;
        this.customerId = customerId;
        this.orderItems = orderItems;
        this.employeeAssignedId = employeeAssignedId;
    }
}
