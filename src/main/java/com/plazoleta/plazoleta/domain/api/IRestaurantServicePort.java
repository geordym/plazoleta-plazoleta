package com.plazoleta.plazoleta.domain.api;

import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;

public interface IRestaurantServicePort {

    void createRestaurant(Restaurant restaurant);
    PaginationCustom<Restaurant> listRestaurants(PaginationParams paginationParams);

}
