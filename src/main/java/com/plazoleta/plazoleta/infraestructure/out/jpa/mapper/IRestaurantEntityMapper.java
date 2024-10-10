package com.plazoleta.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantEntityMapper {

    RestaurantEntity toEntity(Restaurant restaurantModel);
    Restaurant toModel(RestaurantEntity restaurantEntity);

}
