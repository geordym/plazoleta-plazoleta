package com.plazoleta.plazoleta.application.handler;

import com.plazoleta.plazoleta.application.dto.request.CreateOrderRequestDto;

public interface IOrderHandler {

    void createOrder(CreateOrderRequestDto createOrderRequestDto);


}
