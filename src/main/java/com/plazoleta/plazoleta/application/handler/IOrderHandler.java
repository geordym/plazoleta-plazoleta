package com.plazoleta.plazoleta.application.handler;

import com.plazoleta.plazoleta.application.dto.request.CreateOrderRequestDto;
import com.plazoleta.plazoleta.application.dto.response.OrderResponseDto;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

public interface IOrderHandler {

    void createOrder(CreateOrderRequestDto createOrderRequestDto);

    PaginationCustom<OrderResponseDto> findOrdersByEmployeeRestaurant(String status, int page, int size, String sortBy, boolean ascending);


}
