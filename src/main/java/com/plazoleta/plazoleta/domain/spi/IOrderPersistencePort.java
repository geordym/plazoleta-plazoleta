package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    boolean hasActiveOrders(Long customerId);

    PaginationCustom<Order> findOrdersByRestaurantId(OrderStatus orderStatus, Long restaurantId, PaginationParams<OrderSortBy> paginationParams);

}
