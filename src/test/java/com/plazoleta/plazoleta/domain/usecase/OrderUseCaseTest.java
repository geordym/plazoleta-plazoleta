package com.plazoleta.plazoleta.domain.usecase;


import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
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

import static org.junit.jupiter.api.Assertions.*;
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

    @InjectMocks
    private OrderUseCase orderUseCase;

    private Long customerId = 10L;

    @BeforeEach
    void setup(){
        orderUseCase = new OrderUseCase(orderUseCaseValidator, orderPersistencePort, userAuthenticationPort);
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
        PaginationCustom<Order> paginationCustom = orderUseCase.listOrdersByEmployeeRestaurant(paginationParams);
        assertNotNull(paginationCustom);
    }


}
