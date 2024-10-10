package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.plazoleta.domain.enums.RoleEnum;
import com.plazoleta.plazoleta.domain.exception.*;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.plazoleta.plazoleta.domain.util.Constants.NAME_NOT_ONLY_NUMBERS_REGEX;
import static com.plazoleta.plazoleta.domain.util.ExceptionConstants.NUMBER_PREFIX;
import static com.plazoleta.plazoleta.domain.util.ExceptionConstants.PHONE_REGEX;

@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserConnectionPort userConnectionPort;



    @Override
    public void createRestaurant(Restaurant restaurant) {
        validatePhoneNumber(restaurant.getPhone());
        validateRestaurantName(restaurant.getName());
        ensureUserIsRestaurantOwner(restaurant.getOwnerId());
        restaurantPersistencePort.saveRestaurant(restaurant);
    }



    private void validatePhoneNumber(String phoneNumber){
        boolean hasInitialSymbol = phoneNumber.startsWith(NUMBER_PREFIX);
        if(!hasInitialSymbol){
            throw new InvalidPhoneNumberException();
        }

        boolean isValidPhoneNumber = phoneNumber.matches(PHONE_REGEX);
        if(!isValidPhoneNumber){
            throw new InvalidPhoneNumberException();
        }
    }

    private void ensureUserIsRestaurantOwner(Long userId) {
        User user = userConnectionPort.findUserById(userId)
                .orElseThrow(UserDoesNotExistException::new);

        if (!RoleEnum.OWNER.getNameBd().equals(user.getRole().getName())) {
            throw new UserIsNotRestaurantOwnerException();
        }
    }


    private void validateRestaurantName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (name.matches(NAME_NOT_ONLY_NUMBERS_REGEX)) {
            throw new InvalidRestaurantNameException();
        }
    }




}
