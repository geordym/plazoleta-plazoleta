package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.exception.OrderNotFoundException;
import com.plazoleta.plazoleta.domain.exception.OrderNotPendingException;
import com.plazoleta.plazoleta.domain.exception.UnauthorizedAccessException;
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

import java.util.Objects;

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
        Employee employee = getAuthenticatedEmployee();
        PaginationCustom<Order> paginationCustom = orderPersistencePort.findOrdersByRestaurantId(orderStatus, employee.getRestaurantId(), paginationParams);
        return paginationCustom;
    }

    @Override
    public void assignEmployeeToOrder(Long orderId) {
        Order order = orderPersistencePort.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        Employee employee = getAuthenticatedEmployee();
        validateOrderStatusIsPending(order);
        validateIfEmployeeCanWorksInThatOrder(order, employee);

        orderPersistencePort.updateOrderEmployeeAssigned(order.getId(), employee.getId());
    }

    private static void validateOrderStatusIsPending(Order order) {
        if(order.getStatus() != OrderStatus.PENDING){
            throw new OrderNotPendingException();
        }
    }

    private static void validateIfEmployeeCanWorksInThatOrder(Order order, Employee employee) {
        if(!Objects.equals(order.getRestaurant().getId(), employee.getRestaurantId())){
            throw new UnauthorizedAccessException();
        }
    }

    private Employee getAuthenticatedEmployee(){
        Long userId = userAuthenticationPort.getAuthenticatedUserId();
        return userConnectionPort.findEmployeeByUserId(userId).orElseThrow();
    }


}
