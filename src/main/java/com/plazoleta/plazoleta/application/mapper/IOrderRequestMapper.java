package com.plazoleta.plazoleta.application.mapper;


import com.plazoleta.plazoleta.application.dto.request.CreateOrderRequestDto;
import com.plazoleta.plazoleta.application.dto.request.OrderItemRequestDto;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {


    @Mapping(source = "restaurantId", target = "restaurant.id")
    @Mapping(target = "orderItems", source = "orderItems")
    Order toModel(CreateOrderRequestDto createOrderRequestDto);

    List<OrderItem> toOrderItems(List<OrderItemRequestDto> orderItemRequestDtos);

    // Método auxiliar para mapear OrderItemRequestDto a OrderItem
    @Mapping(source = "dishId", target = "dish.id")
    OrderItem toOrderItem(OrderItemRequestDto orderItemRequestDto);
}
