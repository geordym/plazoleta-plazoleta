package com.plazoleta.plazoleta.domain.usecase;


import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.exception.OrderNotFoundException;
import com.plazoleta.plazoleta.domain.exception.OrderNotPendingException;
import com.plazoleta.plazoleta.domain.exception.UnauthorizedAccessException;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.Employee;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.domain.usecase.validator.OrderUseCaseValidator;
import com.plazoleta.plazoleta.util.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderUseCaseTest {

    @Mock
    private OrderUseCaseValidator orderUseCaseValidator;

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IUserAuthenticationPort userAuthenticationPort;

    @Mock
    private IUserConnectionPort userConnectionPort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    private Long customerId = 10L;

    @BeforeEach
    void setup(){
        orderUseCase = new OrderUseCase(orderUseCaseValidator, orderPersistencePort, userAuthenticationPort, userConnectionPort);
    }

    @Test
    void testCreateOrderCallValidator(){
        Order order = new Order();

        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(customerId);

        orderUseCase.createOrder(order);

        verify(orderUseCaseValidator).validateCreateOrder(order, customerId);
    }

    @Test
    void testCreateOrderIsPendingWhenSave(){
        Order order = DataProvider.getValidOrder();

        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(customerId);

        orderUseCase.createOrder(order);
        verify(orderUseCaseValidator).validateCreateOrder(order, customerId);
    }

    @Test
    void testCreateOrderIsStatusPendingWhenSave() {
        Order order = DataProvider.getValidOrder();

        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(customerId);

        orderUseCase.createOrder(order);
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderPersistencePort).saveOrder(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertNotNull(capturedOrder);
        assertEquals(OrderStatus.PENDING, capturedOrder.getStatus());
    }




    @Test
    void testListOrdersPaginated(){
        PaginationParams<OrderSortBy> paginationParams = DataProvider.orderPaginationParamsValid();
        PaginationCustom<Order> paginationCustomTest = DataProvider.orderPaginationCustom(paginationParams.getPage(), paginationParams.getSize(), paginationParams.isAscending());
        OrderStatus orderStatus = OrderStatus.READY;
        Employee employee = DataProvider.validEmployee();
        Long restaurantId = employee.getRestaurantId();

        when(userConnectionPort.findEmployeeByUserId(any())).thenReturn(Optional.of(employee));
        when(orderPersistencePort.findOrdersByRestaurantId(orderStatus, restaurantId, paginationParams)).thenReturn(paginationCustomTest);

        PaginationCustom<Order> paginationCustom = orderUseCase.listOrdersByEmployeeRestaurant(orderStatus, paginationParams);
        assertNotNull(paginationCustom);
    }


    @Test
    void testAssignEmployeeToOrderNoExist(){
        Long orderId = 10L;

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderUseCase.assignEmployeeToOrder(orderId));
    }

    @Test
    void testAssignEmployeeToOrderStatusIsNotPending(){
        Long orderId = 10L;
        Long userId = 1L;
        Order order = DataProvider.orderWithPreparingStatus();
        Employee employee = DataProvider.validEmployee();

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(Optional.of(order));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userId);
        when(userConnectionPort.findEmployeeByUserId(userId)).thenReturn(Optional.of(employee));

        assertThrows(OrderNotPendingException.class, () -> orderUseCase.assignEmployeeToOrder(orderId));
    }

    @Test
    void testAssignEmployeeToOrderStatusIsPending(){
        Long orderId = 10L;
        Long userId = 1L;
        Employee employee = DataProvider.validEmployee();
        Order order = DataProvider.orderWithPendingStatus();
        order.getRestaurant().setId(employee.getRestaurantId());

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(Optional.of(order));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userId);
        when(userConnectionPort.findEmployeeByUserId(userId)).thenReturn(Optional.of(employee));


        assertDoesNotThrow(() -> orderUseCase.assignEmployeeToOrder(orderId));
    }

    @Test
    void testAssignEmployeeWhenOrderIsNotOfTheRestaurantEmployeeWorks(){
        Long orderId = 10L;
        Long userId = 1L;
        Order order = DataProvider.orderWithPendingStatus();
        Restaurant restaurant = DataProvider.getValidOrder().getRestaurant();
        order.setRestaurant(restaurant);
        Employee employee = DataProvider.validEmployee();
        employee.setRestaurantId(restaurant.getId() + 1);

        when(orderPersistencePort.findOrderById(orderId)).thenReturn(Optional.of(order));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userId);
        when(userConnectionPort.findEmployeeByUserId(userId)).thenReturn(Optional.of(employee));


        assertThrows(UnauthorizedAccessException.class, () -> orderUseCase.assignEmployeeToOrder(orderId));
    }



}
