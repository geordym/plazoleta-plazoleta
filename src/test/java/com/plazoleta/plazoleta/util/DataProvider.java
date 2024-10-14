package com.plazoleta.plazoleta.util;

import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.Employee;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static Order orderWithPendingStatus(){
        return new Order(1L, new Restaurant(), LocalDateTime.now(), OrderStatus.PENDING, 1L, new ArrayList<>(), 1L);
    }

    public static Order orderWithPreparingStatus(){
        return new Order(1L, new Restaurant(), LocalDateTime.now(), OrderStatus.PREPARING, 1L, new ArrayList<>(), 1L);
    }

    public static Employee validEmployee(){
        return new Employee(1L, 1L, 1L);
    }

    public static PaginationParams<OrderSortBy> orderPaginationParamsValid(){
        return new PaginationParams<>(0, 2, OrderSortBy.STATUS, true);
    }

    public static PaginationCustom<Order> orderPaginationCustom(int page, int size, boolean isAscending) {
        // Crear una lista de órdenes
        List<Order> orders = new ArrayList<>();

        // Generar datos de ejemplo
        for (int i = 1; i <= size; i++) {
            // Crear un restaurante de ejemplo
            Restaurant restaurant = new Restaurant();
            restaurant.setId((long) i);
            restaurant.setName("Restaurant " + i);
            restaurant.setNit(123456789L + i);
            restaurant.setAddress("Address " + i);
            restaurant.setPhone("123-456-789" + i);
            restaurant.setLogoUrl("http://example.com/logo" + i + ".png");
            restaurant.setOwnerId(100L + i);

            // Crear elementos de la orden
            List<OrderItem> orderItems = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId((long) (i * 10 + j));
                orderItem.setDish(new Dish((long) j));
                orderItem.setQuantity(j);
                orderItems.add(orderItem);
            }

            // Crear la orden
            Order order = new Order();
            order.setId((long) i);
            order.setRestaurant(restaurant);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.PENDING);
            order.setCustomerId(200L + i);
            order.setOrderItems(orderItems);

            orders.add(order);
        }

        PaginationCustom<Order> paginationCustom = new PaginationCustom<>();
        paginationCustom.setContent(orders);
        paginationCustom.setPageSize(size);
        paginationCustom.setTotalElements(orders.size());
        paginationCustom.setTotalPages((int) Math.ceil((double) orders.size() / size));
        paginationCustom.setLast(page >= paginationCustom.getTotalPages() - 1);

        return paginationCustom;
    }


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
