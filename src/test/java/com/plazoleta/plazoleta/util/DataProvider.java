package com.plazoleta.plazoleta.util;

import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.model.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {


    public static Order getValidOrder() {
        Order order = new Order();
        order.setId(10L);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Valid Restaurant");
        order.setRestaurant(restaurant);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem item1 = new OrderItem();
        item1.setOrderItemId(1L);
        item1.setDish(new Dish(1L));
        item1.setQuantity(2);
        orderItems.add(item1);
        order.setOrderItems(orderItems);
        return order;
    }

    public static List<OrderItem> orderItemsWithOutDishId() {
        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem item1 = new OrderItem();
        item1.setDish(new Dish());

        OrderItem item2 = new OrderItem();
        item2.setDish(new Dish());

        orderItems.add(item1);
        orderItems.add(item2);

        return orderItems;
    }

    public static List<OrderItem> orderItemsWithNullDish() {
        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem item1 = new OrderItem();
        item1.setDish(null);

        OrderItem item2 = new OrderItem();
        item2.setDish(null);

        orderItems.add(item1);
        orderItems.add(item2);

        return orderItems;
    }

    public static List<OrderItem> orderItemsWithDishIdZero() {
        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem item1 = new OrderItem();
        item1.setDish(new Dish(0L));

        OrderItem item2 = new OrderItem();
        item2.setDish(new Dish(0L));

        orderItems.add(item1);
        orderItems.add(item2);

        return orderItems;
    }

    public static List<OrderItem> orderItemsWithZeroQuantity() {
        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem item1 = new OrderItem();
        item1.setDish(new Dish(1L));
        item1.setQuantity(0);


        OrderItem item2 = new OrderItem();
        item2.setDish(new Dish(2L));
        item2.setQuantity(0);

        orderItems.add(item1);
        orderItems.add(item2);

        return orderItems;
    }

}
