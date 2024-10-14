package com.plazoleta.plazoleta.application.handler.impl;

import com.plazoleta.plazoleta.application.dto.request.CreateOrderRequestDto;
import com.plazoleta.plazoleta.application.dto.response.OrderResponseDto;
import com.plazoleta.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.plazoleta.application.mapper.IOrderRequestMapper;
import com.plazoleta.plazoleta.application.mapper.IOrderResponseMapper;
import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderHandlerImpl implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;

    @Override
    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        orderServicePort.createOrder(orderRequestMapper.toModel(createOrderRequestDto));
    }

    @Override
    public PaginationCustom<OrderResponseDto> findOrdersByEmployeeRestaurant(String status, int page, int size, String sortBy, boolean ascending) {
        OrderSortBy orderSortBy = OrderSortBy.fromValue(sortBy);
        OrderStatus orderStatus = null;
        if(!status.isEmpty()){
            orderStatus = OrderStatus.valueOf(status);
        }

        PaginationCustom<Order> orderList = orderServicePort.listOrdersByEmployeeRestaurant(orderStatus, new PaginationParams<OrderSortBy>(page, size, orderSortBy, ascending));
        return orderResponseMapper.toPaginationDto(orderList);
    }

    @Override
    public void assignOrderToEmployee(Long orderId) {
        orderServicePort.assignEmployeeToOrder(orderId);
    }

    @Transactional
    @Override
    public void notifyOrderIsReady(Long orderId) {
        orderServicePort.notifyClientOrderIsReady(orderId);
    }


}
