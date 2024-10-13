package com.plazoleta.plazoleta.domain.api;

import com.plazoleta.plazoleta.domain.enums.RestaurantSortBy;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

public interface IRestaurantServicePort {


    void makeOrder();

    void createRestaurant(Restaurant restaurant);
    PaginationCustom<Restaurant> listRestaurants(PaginationParams<RestaurantSortBy> paginationParams);

    Restaurant findRestaurantByOwnerId(Long ownerId);
    Restaurant findRestaurantById(Long restaurantId);
}
