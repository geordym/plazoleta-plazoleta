package com.plazoleta.plazoleta.domain.api;

import com.plazoleta.plazoleta.domain.enums.DishEnumSortBy;
import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.util.DishFilter;

public interface IDishServicePort {

    void createDish(Dish dish);

    void updateDish(Long dishId, Integer price, String description);

    void toogleDishStatus(Long dishId, boolean active);

    PaginationCustom<Dish> listDishesOfRestaurant(DishFilter dishFilter, Long restaurantId, PaginationParams<DishEnumSortBy> paginationParams);

}
