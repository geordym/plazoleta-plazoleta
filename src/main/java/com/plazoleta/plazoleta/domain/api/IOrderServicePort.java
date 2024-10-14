package com.plazoleta.plazoleta.domain.api;

import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

public interface IOrderServicePort {
    void createOrder(Order order);

    PaginationCustom<Order> listOrdersByEmployeeRestaurant(OrderStatus orderStatus, PaginationParams<OrderSortBy> paginationParams);

    void assignEmployeeToOrder(Long orderId);

}
