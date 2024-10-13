package com.plazoleta.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderItemEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.plazoleta.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    private List<OrderItemEntity> mapOrderItems(Order order, OrderEntity orderEntity) {
        return order.getOrderItems().stream()
                .map(orderItem -> {
                    OrderItemEntity orderItemEntity = orderEntityMapper.toEntity(orderItem);
                    orderItemEntity.setOrder(orderEntity); // Asigna el Order a cada OrderItemEntity
                    return orderItemEntity;
                })
                .toList();
    }

}
