package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.enums.DishEnumSortBy;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.util.DishFilter;

import java.util.List;
import java.util.Optional;

public interface IDishPersistencePort {
    Dish saveDish(Dish dish);
    Optional<Dish> findDishById(Long dishId);

    Dish updateDish(Dish dish);

    PaginationCustom<Dish> listDishByRestaurantId(DishFilter dishFilter, Long restaurantId, PaginationParams<DishEnumSortBy> paginationParams);

    List<Dish> findAllDishByIdAndRestaurantId(List<Long> dishList, Long restaurantId);
}
