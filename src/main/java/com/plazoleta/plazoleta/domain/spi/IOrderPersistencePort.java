package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.model.Order;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    boolean hasActiveOrders(Long customerId);
}
