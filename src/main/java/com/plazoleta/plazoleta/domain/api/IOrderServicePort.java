package com.plazoleta.plazoleta.domain.api;

import com.plazoleta.plazoleta.domain.model.Order;

public interface IOrderServicePort {
    void createOrder(Order order);
}
