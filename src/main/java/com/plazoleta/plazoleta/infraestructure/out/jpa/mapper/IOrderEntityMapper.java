package com.plazoleta.plazoleta.infraestructure.out.jpa.mapper;


import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.OrderItemEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {

    @Mappings({
            @Mapping(target = "orderItems", ignore = true) // Ignorar la lista de orderItems
    })
    Order toModel(OrderEntity order);


    Order toModelWithOrderItems(OrderEntity order);


    @Mappings({
            @Mapping(target = "order", ignore = true),
            @Mapping(target = "dish", source = "dish")
    })
    OrderItem toModel(OrderItemEntity orderItemEntity);

    @Mapping(source = "restaurant", target = "restaurant")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderEntity toEntity(Order order);

    RestaurantEntity toEntity(Restaurant restaurant);

    @Mapping(source = "dish.id", target = "dish.id")
    OrderItemEntity toEntity(OrderItem orderItem);


    @Mapping(source = "dish.id", target = "dish.id")
    List<OrderItemEntity> toEntity(List<OrderItem> orderItem);


}
