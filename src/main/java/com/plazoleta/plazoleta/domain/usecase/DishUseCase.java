package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IDishServicePort;
import com.plazoleta.plazoleta.domain.enums.DishEnumSortBy;
import com.plazoleta.plazoleta.domain.exception.DishNotFoundException;
import com.plazoleta.plazoleta.domain.exception.RestaurantNotFoundException;
import com.plazoleta.plazoleta.domain.exception.UnauthorizedAccessException;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.util.DishFilter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserAuthenticationPort userAuthenticationPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public void updateDish(Long dishId, Integer price, String description) {
        Dish dish = dishPersistencePort.findDishById(dishId).orElseThrow(DishNotFoundException::new);
        verifyOwnerAccess(dish.getRestaurantId());

        dish.setPrice(price);
        dish.setDescription(description);
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void toogleDishStatus(Long dishId, boolean active) {
        Dish dish = dishPersistencePort.findDishById(dishId).orElseThrow(DishNotFoundException::new);
        verifyOwnerAccess(dish.getRestaurantId());

        dish.setActive(active);
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public PaginationCustom<Dish> listDishesOfRestaurant(DishFilter dishFilter, Long restaurantId, PaginationParams<DishEnumSortBy> paginationParams) {
        verifyRestaurantExists(restaurantId);
        return dishPersistencePort.listDishByRestaurantId(dishFilter,restaurantId, paginationParams);
    }

    @Override
    public void createDish(Dish dish) {
        verifyOwnerAccess(dish.getRestaurantId());
        dishPersistencePort.saveDish(dish);
    }


    private void verifyOwnerAccess(Long restaurantId) {
        Long authenticatedOwnerId = userAuthenticationPort.getAuthenticatedUserId();
        Restaurant restaurant = verifyRestaurantExists(restaurantId);

        if (!restaurant.getOwnerId().equals(authenticatedOwnerId)) {
            throw new UnauthorizedAccessException();
        }
    }

    //TODO: Check the name of that method
    public Restaurant verifyRestaurantExists(Long restaurantId) {
        return restaurantPersistencePort.findRestaurantById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
    }

}
