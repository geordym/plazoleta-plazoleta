package com.plazoleta.plazoleta.domain.usecase.validator;


import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.exception.InvalidDishForRestaurantException;
import com.plazoleta.plazoleta.domain.exception.OrderAlreadyInProgressException;
import com.plazoleta.plazoleta.domain.exception.RestaurantNotFoundException;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IDishEntityMapper;
import com.plazoleta.plazoleta.util.DataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderUseCaseValidatorTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IOrderPersistencePort orderPersistencePort;



    private OrderUseCaseValidator orderUseCaseValidator;



    @BeforeEach
    void setup() {
        orderUseCaseValidator = new OrderUseCaseValidator(restaurantPersistencePort, dishPersistencePort, orderPersistencePort);
    }

    @Test
    void testCreateOrderWhenHaveAlreadyOrder() {
        Order order = DataProvider.getValidOrder();

        when(orderPersistencePort.hasActiveOrders(order.getCustomerId())).thenReturn(true);

        assertThrows(OrderAlreadyInProgressException.class, () -> {
            orderUseCaseValidator.validateIfHasAnOrderInProgress(order);
        });
    }

    @Test
    void testCreateOrderWhenNotHaveAlreadyOrder() {
        Order order = DataProvider.getValidOrder();

        when(orderPersistencePort.hasActiveOrders(order.getCustomerId())).thenReturn(false);

        assertDoesNotThrow( () -> {
            orderUseCaseValidator.validateIfHasAnOrderInProgress(order);
        });
    }

    @Test
    void testCreateOrder() {
        Order order = DataProvider.getValidOrder();

        assertDoesNotThrow(() -> {
            orderUseCaseValidator.validateCreateOrder(order);
        });
    }

    @Test
    void testCreateOrderNull() {
        Order order = null;

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateCreateOrder(order);
        });
    }

    @Test
    void testCreateOrderRestaurantNull() {
        Order order = new Order();
        order.setRestaurant(null);

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateNotNull(order);
        });
    }

    @Test
    void testCreateOrderOrderDateNull() {
        Order order = new Order();
        order.setRestaurant(new Restaurant());
        order.setOrderDate(null);

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateNotNull(order);
        });
    }

    @Test
    void testCreateOrderStatusNull() {
        Order order = new Order();
        order.setRestaurant(new Restaurant());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(null);

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateNotNull(order);
        });
    }

    @Test
    void testCreateOrderCustomerIdNull() {
        Order order = new Order();
        order.setRestaurant(new Restaurant());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomerId(null);

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateNotNull(order);
        });
    }


    @Test
    void testCreateOrderOrderItemsNullOrEmpty() {
        Order order = new Order();
        order.setRestaurant(new Restaurant());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomerId(1L);
        order.setOrderItems(null);

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateNotNull(order);
        });

        order.setOrderItems(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateNotNull(order);
        });
    }

    @Test
    void testCreateOrderRestaurantNotExist() {
        Order order = DataProvider.getValidOrder();

        when(restaurantPersistencePort.existsRestaurantById(anyLong())).thenReturn(false);

        assertThrows(RestaurantNotFoundException.class, () -> {
            orderUseCaseValidator.validateRestaurantExist(order);
        });
    }

    @Test
    void testCreateOrderDishesDoNotExistsInRestaurant() {
        Order order = DataProvider.getValidOrder();

        when(dishPersistencePort.findAllDishByIdAndRestaurantId(anyList(), anyLong())).thenReturn(new ArrayList<>());

        assertThrows(InvalidDishForRestaurantException.class, () -> {
            orderUseCaseValidator.validateDishesExistInRestaurant(order);
        });
    }

    @Test
    void testCreateOrderDishesExistsInRestaurant() {
        Order order = DataProvider.getValidOrder();

        when(dishPersistencePort.findAllDishByIdAndRestaurantId(anyList(), anyLong())).
                thenReturn(order.getOrderItems().
                        stream().map(OrderItem::getDish).toList());

        assertDoesNotThrow(() -> {
            orderUseCaseValidator.validateDishesExistInRestaurant(order);
        });
    }

    @Test
    void testCreateOrderWithRestaurantId(){
        Order order = DataProvider.getValidOrder();

        assertDoesNotThrow( () -> {
            orderUseCaseValidator.validateHasRestaurantId(order);
        });
    }

    @Test
    void testCreateOrderWithoutRestaurantId(){
        Order order = DataProvider.getValidOrder();
        order.getRestaurant().setId(null);

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateHasRestaurantId(order);
        });
    }

    @Test
    void testCreateOrderWithoutDishId(){
        Order order = DataProvider.getValidOrder();
        order.setOrderItems(DataProvider.orderItemsWithOutDishId());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateOrdersHaveDish(order);
        });
    }

    @Test
    void testCreateOrderWithDishNull(){
        Order order = DataProvider.getValidOrder();
        order.setOrderItems(DataProvider.orderItemsWithNullDish());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateOrdersHaveDish(order);
        });
    }

    @Test
    void testCreateOrderWithDishIdZero(){
        Order order = DataProvider.getValidOrder();
        order.setOrderItems(DataProvider.orderItemsWithDishIdZero());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateOrdersHaveDish(order);
        });
    }


    @Test
    void testCreateOrderWithItemsValid(){
        Order order = DataProvider.getValidOrder();

        assertDoesNotThrow(() -> {
            orderUseCaseValidator.validateOrderItemsHasValidQuantity(order);
        });
    }


    @Test
    void testCreateOrderWithItemsEmpty(){
        Order order = DataProvider.getValidOrder();
        order.setOrderItems(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateOrderItemsHasValidQuantity(order);
        });
    }

    @Test
    void testCreateOrderWithZeroQuantity(){
        Order order = DataProvider.getValidOrder();
        order.setOrderItems(DataProvider.orderItemsWithZeroQuantity());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCaseValidator.validateOrderItemsHasValidQuantity(order);
        });
    }



}
