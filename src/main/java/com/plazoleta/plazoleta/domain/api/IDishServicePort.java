package com.plazoleta.plazoleta.domain.api;

import com.plazoleta.plazoleta.domain.model.Dish;

public interface IDishServicePort {

    void createDish(Dish dish);

    void updateDish(Long dishId, Integer price, String description);

}
