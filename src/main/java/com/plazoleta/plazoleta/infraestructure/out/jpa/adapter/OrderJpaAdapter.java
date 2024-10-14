package com.plazoleta.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderItemEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;


    @Override
    public Order saveOrder(Order order) {
        final OrderEntity orderEntity= orderEntityMapper.toEntity(order);
        List<OrderItemEntity> orderItemsAimed = mapOrderItems(order, orderEntity);
        orderEntity.setOrderItems(orderItemsAimed);
        return orderEntityMapper.toModel(orderRepository.save(orderEntity));
    }

    @Override
    public boolean hasActiveOrders(Long customerId) {
        List<OrderStatus> statusList = new ArrayList<>();
        statusList.add(OrderStatus.PREPARING);
        statusList.add(OrderStatus.PENDING);
        statusList.add(OrderStatus.READY);
        Long activeOrders = orderRepository.countActiveOrdersByCustomerId(customerId, statusList);
        return activeOrders > 0;
    }

    @Override
    public PaginationCustom<Order> findOrdersByRestaurantId(OrderStatus statusFilter, Long restaurantId, PaginationParams<OrderSortBy> paginationParams) {
        PageRequest pageRequest = PageRequest.of(
                paginationParams.getPage(),
                paginationParams.getSize(),
                paginationParams.isAscending() ? Sort.by(paginationParams.getSortBy().entityAttribute()).ascending() : Sort.by(paginationParams.getSortBy().entityAttribute()).descending()
        );

        Page<OrderEntity> orderPage = (statusFilter == null )
                ? orderRepository.findOrdersByRestaurantId(restaurantId, pageRequest)
                : orderRepository.findOrdersByRestaurantIdAndStatus(restaurantId, statusFilter, pageRequest);

        List<Order> orderList = orderPage.getContent()
                .stream()
                .map(orderEntityMapper::toModelWithOrderItems)
                .toList();

        return new PaginationCustom<>(
                orderList,
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                orderPage.isLast()
        );
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId).map(orderEntityMapper::toModel);
    }

    @Override
    public void updateOrderEmployeeAssigned(Long orderId, Long employeeId) {
       orderRepository.updateOrderEmployeeAssigned(employeeId, orderId, OrderStatus.PREPARING);
    }

    @Override
    public void updateOrderReclaimCode(Long orderId, Integer reclaimCode) {
        orderRepository.updateOrderReclaimCode(orderId, reclaimCode);
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        orderRepository.updateOrderStatus(orderStatus, orderId);
    }

    private List<OrderItemEntity> mapOrderItems(Order order, OrderEntity orderEntity) {
        return order.getOrderItems().stream()
                .map(orderItem -> {
                    OrderItemEntity orderItemEntity = orderEntityMapper.toEntity(orderItem);
                    orderItemEntity.setOrder(orderEntity);
                    return orderItemEntity;
                })
                .toList();
    }

}
