package com.plazoleta.plazoleta.infraestructure.out.jpa.mapper;


import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.DishEntity;
import com.plazoleta.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    @Mapping(source = "restaurantId", target = "restaurant")
    DishEntity toEntity(Dish dish);

    default RestaurantEntity mapRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            return null;
        }
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(restaurantId);
        return restaurant;
    }

    @Mapping(source = "restaurant.id", target = "restaurantId")
    Dish toModel(DishEntity dish);

}
