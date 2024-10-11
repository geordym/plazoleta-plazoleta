package com.plazoleta.plazoleta.application.mapper;

import com.plazoleta.plazoleta.application.dto.response.DishResponseDto;
import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    DishResponseDto toDto(Dish dish);

    default PaginationCustom<DishResponseDto> toPaginationDto(PaginationCustom<Dish> dishPaginationCustom) {
        PaginationCustom<DishResponseDto> pagination = new PaginationCustom<>();
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
