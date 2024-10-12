package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.usecase.validator.OrderUseCaseValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {


    private final OrderUseCaseValidator orderUseCaseValidator;
    private final IOrderPersistencePort orderPersistencePort;

    @Override
    public void createOrder(Order order) {
        orderUseCaseValidator.validateCreateOrder(order);
        order.setStatus(OrderStatus.PENDING);
        orderPersistencePort.saveOrder(order);
    }


}
