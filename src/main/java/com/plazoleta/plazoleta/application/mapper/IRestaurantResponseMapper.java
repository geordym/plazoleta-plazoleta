package com.plazoleta.plazoleta.application.mapper;

import com.plazoleta.plazoleta.application.dto.response.RestaurantShortResponseDto;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantResponseMapper {

    @Mapping(source = "logoUrl", target = "urlLogo")
    RestaurantShortResponseDto toDto(Restaurant restaurant);

    default PaginationCustom<RestaurantShortResponseDto> toPaginationDto(PaginationCustom<Restaurant> restaurantPaginationCustom) {
        PaginationCustom<RestaurantShortResponseDto> pagination = new PaginationCustom<>();
        pagination.setContent(restaurantPaginationCustom.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        pagination.setPageNumber(restaurantPaginationCustom.getPageNumber());
        pagination.setPageSize(restaurantPaginationCustom.getPageSize());
        pagination.setTotalElements(restaurantPaginationCustom.getTotalElements());
        pagination.setTotalPages(restaurantPaginationCustom.getTotalPages());
        pagination.setLast(restaurantPaginationCustom.isLast());
        return pagination;
    }

}
