package com.plazoleta.plazoleta.application.mapper;


import com.plazoleta.plazoleta.application.dto.response.DishResponseDto;
import com.plazoleta.plazoleta.application.dto.response.OrderItemResponseDto;
import com.plazoleta.plazoleta.application.dto.response.OrderResponseDto;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.OrderItem;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

    OrderResponseDto toDto(Order order);

    OrderItemResponseDto toDto(OrderItem orderItem);

    default PaginationCustom<OrderResponseDto> toPaginationDto(PaginationCustom<Order> dishPaginationCustom) {
        PaginationCustom<OrderResponseDto> pagination = new PaginationCustom<>();
        pagination.setContent(dishPaginationCustom.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        pagination.setPageNumber(dishPaginationCustom.getPageNumber());
        pagination.setPageSize(dishPaginationCustom.getPageSize());
        pagination.setTotalElements(dishPaginationCustom.getTotalElements());
        pagination.setTotalPages(dishPaginationCustom.getTotalPages());
        pagination.setLast(dishPaginationCustom.isLast());
        return pagination;
    }
}
