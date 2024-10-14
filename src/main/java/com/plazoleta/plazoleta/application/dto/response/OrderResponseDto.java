package com.plazoleta.plazoleta.application.dto.response;

import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.model.Restaurant;
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
public class OrderResponseDto {
    private Long id;
    private Restaurant restaurant;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Long customerId;
    private List<OrderItemResponseDto> orderItems;

    private Integer reclaimCode;
}
