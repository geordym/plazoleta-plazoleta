package com.plazoleta.plazoleta.domain.usecase.validator;

import com.plazoleta.plazoleta.domain.exception.DishNotFoundException;
import com.plazoleta.plazoleta.domain.exception.InvalidDishForRestaurantException;
import com.plazoleta.plazoleta.domain.exception.OrderAlreadyInProgressException;
import com.plazoleta.plazoleta.domain.exception.RestaurantNotFoundException;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderUseCaseValidator {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;



    public static final String ORDER_CANNOT_BE_NULL = "Order cannot be null";
    public static final String RESTAURANT_CANNOT_BE_NULL = "Restaurant cannot be null";
    public static final String ORDER_DATE_CANNOT_BE_NULL = "Order date cannot be null";
    public static final String ORDER_STATUS_CANNOT_BE_NULL = "Order status cannot be null";
    public static final String CUSTOMER_ID_CANNOT_BE_NULL = "Customer ID cannot be null";
    public static final String ORDER_ITEMS_CANNOT_BE_NULL_OR_EMPTY = "Order items cannot be null or empty";

    public void validateCreateOrder(Order order, Long customerId){
        validateNotNull(order);
        validateIfHasAnOrderInProgress(customerId);
        validateHasRestaurantId(order);
        validateOrderItemsHasValidQuantity(order);
        validateOrdersHaveDish(order);
        validateRestaurantExist(order);
        validateDishesExistInRestaurant(order);
    }

    public void validateIfHasAnOrderInProgress(Long customerId){
        boolean haveOrderInProgress = orderPersistencePort.hasActiveOrders(customerId);
        if(haveOrderInProgress){
            throw new OrderAlreadyInProgressException();
        }
    }


    public void validateNotNull(Order order) {
        if (order == null) {
            throw new IllegalArgumentException(ORDER_CANNOT_BE_NULL);
        }

        if (order.getRestaurant() == null) {
            throw new IllegalArgumentException(RESTAURANT_CANNOT_BE_NULL);
        }

        if (order.getOrderDate() == null) {
            throw new IllegalArgumentException(ORDER_DATE_CANNOT_BE_NULL);
        }

        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException(ORDER_ITEMS_CANNOT_BE_NULL_OR_EMPTY);
        }
    }

    public void validateHasRestaurantId(Order order) {
        if (order == null || order.getRestaurant() == null || order.getRestaurant().getId() == null) {
            throw new IllegalArgumentException();
        }
    }

    public void validateOrderItemsHasValidQuantity(Order order) {
        if (order == null || order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (OrderItem item : order.getOrderItems()) {
            if (item == null || item.getQuantity() <= 0) {
                throw new IllegalArgumentException();
            }
        }
    }


    public void validateOrdersHaveDish(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (OrderItem item : orderItems) {
            if (item == null || item.getDish() == null || item.getDish().getId() == null || item.getDish().getId() == 0) {
                throw new IllegalArgumentException();
            }
        }
    }


    public void validateRestaurantExist(Order order){
        boolean existsRestaurant = restaurantPersistencePort.existsRestaurantById(order.getRestaurant().getId());
        if(!existsRestaurant){
            throw new RestaurantNotFoundException();
        }
    }

    public void validateDishesExistInRestaurant(Order order){
        Long restaurantId = order.getRestaurant().getId();
        List<Long> dishesIdList = order.getOrderItems().stream().map(orderItem -> orderItem.getDish().getId()).toList();
        List<Dish> dishFounded = dishPersistencePort.findAllDishByIdAndRestaurantId(dishesIdList, restaurantId);
        for (Long dishId : dishesIdList) {
            if (dishFounded.stream().noneMatch(dish -> dish.getId().equals(dishId))) {
                throw new InvalidDishForRestaurantException(dishId, restaurantId);
            }
        }
    }


}
