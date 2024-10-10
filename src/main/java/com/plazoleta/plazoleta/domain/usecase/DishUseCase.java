package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IDishServicePort;
import com.plazoleta.plazoleta.domain.exception.RestaurantNotFoundException;
import com.plazoleta.plazoleta.domain.exception.UnauthorizedAccessException;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserAuthenticationPort userAuthenticationPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public void createDish(Dish dish) {
        verifyOwnerAccess(dish.getRestaurantId());
        dishPersistencePort.saveDish(dish);
    }


    private void verifyOwnerAccess(Long restaurantId) {
        User authenticatedOwner = userAuthenticationPort.getAuthenticatedUser();
        Restaurant restaurant = verifyRestaurantExists(restaurantId);

        if (!restaurant.getOwnerId().equals(authenticatedOwner.getId())) {
            throw new UnauthorizedAccessException();
        }
    }

    //TODO: Check the name of that method
    private Restaurant verifyRestaurantExists(Long restaurantId) {
        return restaurantPersistencePort.findRestaurantById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
    }

}
