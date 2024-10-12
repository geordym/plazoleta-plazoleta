package com.plazoleta.plazoleta.domain.usecase;


import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
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

    @InjectMocks
    private OrderUseCase orderUseCase;

    @BeforeEach
    void setup(){
    }

    @Test
    void testCreateOrderCallValidator(){
        Order order = new Order();

        orderUseCase.createOrder(order);

        verify(orderUseCaseValidator).validateCreateOrder(order);
    }

    @Test
    void testCreateOrderIsPendingWhenSave(){
        Order order = DataProvider.getValidOrder();

        orderUseCase.createOrder(order);

        verify(orderUseCaseValidator).validateCreateOrder(order);
    }

    @Test
    void testCreateOrderIsStatusPendingWhenSave() {
        Order order = DataProvider.getValidOrder();

        orderUseCase.createOrder(order);

        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderPersistencePort).saveOrder(orderCaptor.capture());
        Order capturedOrder = orderCaptor.getValue();
        assertNotNull(capturedOrder);
        assertEquals(OrderStatus.PENDING, capturedOrder.getStatus());
    }






}
