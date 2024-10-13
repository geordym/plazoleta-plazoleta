package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.external.Employee;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.domain.usecase.validator.OrderUseCaseValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final OrderUseCaseValidator orderUseCaseValidator;
    private final IOrderPersistencePort orderPersistencePort;
    private final IUserAuthenticationPort userAuthenticationPort;
    private final IUserConnectionPort userConnectionPort;

    @Override
    public void createOrder(Order order) {
        Long customerId = userAuthenticationPort.getAuthenticatedUserId();
        orderUseCaseValidator.validateCreateOrder(order, customerId);
        order.setStatus(OrderStatus.PENDING);
        order.setCustomerId(customerId);
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public PaginationCustom<Order> listOrdersByEmployeeRestaurant(OrderStatus orderStatus, PaginationParams<OrderSortBy> paginationParams) {
        Long userId = userAuthenticationPort.getAuthenticatedUserId();
        Employee employee = userConnectionPort.findEmployeeByUserId(userId).orElseThrow();
        PaginationCustom<Order> paginationCustom = orderPersistencePort.findOrdersByRestaurantId(orderStatus, employee.getRestaurantId(), paginationParams);
        return paginationCustom;
    }


}
