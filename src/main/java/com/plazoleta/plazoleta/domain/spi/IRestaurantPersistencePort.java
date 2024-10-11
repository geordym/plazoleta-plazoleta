package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant);
    Optional<Restaurant> findRestaurantById(Long resturantId);
    PaginationCustom<Restaurant> findAllRestaurant(PaginationParams<RestaurantSortBy> paginationParams);
}
