package com.plazoleta.plazoleta.domain.usecase;


import com.plazoleta.plazoleta.domain.enums.DishEnumSortBy;
import com.plazoleta.plazoleta.domain.enums.RoleEnum;
import com.plazoleta.plazoleta.domain.exception.RestaurantNotFoundException;
import com.plazoleta.plazoleta.domain.exception.UnauthorizedAccessException;
import com.plazoleta.plazoleta.domain.exception.UserIsNotRestaurantOwnerException;
import com.plazoleta.plazoleta.domain.model.Dish;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.Role;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IDishPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.domain.util.DishFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishUseCaseTest {


    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IUserConnectionPort userConnectionPort;

    @Mock
    private IUserAuthenticationPort userAuthenticationPort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;



    private DishUseCase dishUseCase;



    private Dish validDish;

    private User userOwner;

    private Restaurant validRestaurant;

    @Mock
    private DishFilter dishFilter;

    @Mock
    private PaginationParams<DishEnumSortBy> paginationParams;

    @BeforeEach
    void setup(){
        Role roleOwner = new Role(RoleEnum.OWNER.getId(), RoleEnum.OWNER.getNameBd());
        userOwner = new User(0L, "David", "Montero", 1018522721L, "+573026468094", LocalDate.now(), "ggeordymm@gmail.com", "testing1234");
        userOwner.setRole(roleOwner);

        dishUseCase = new DishUseCase(dishPersistencePort, userAuthenticationPort, restaurantPersistencePort);
        validRestaurant = new Restaurant("Pizza Pro", 99999L, "Esquina 49 diagonal 9", "+573026468094", "logo.png", userOwner.getId());

        validDish = new Dish("Spaghetti Bolognese", 15, "Classic Italian pasta dish.", "https://example.com/image.jpg", "Pasta", validRestaurant.getId());

    }

    @Test
    public void testListDishesOfRestaurant_WhenRestaurantExists() {
        Long restaurantId = 1L;
        PaginationCustom<Dish> expectedDishes = new PaginationCustom<>(List.of(), 0, 10, 0, 1, true);

        when(restaurantPersistencePort.findRestaurantById(restaurantId)).thenReturn(Optional.of(new Restaurant()));

        when(dishPersistencePort.listDishByRestaurantId(dishFilter, restaurantId, paginationParams)).thenReturn(expectedDishes);

        PaginationCustom<Dish> result = dishUseCase.listDishesOfRestaurant(dishFilter, restaurantId, paginationParams);

        assertThat(result).isEqualTo(expectedDishes);
        //verify(dishUseCaseMock).verifyRestaurantExists(restaurantId);
        //verify(dishPersistencePort).listDishByRestaurantId(dishFilter, restaurantId, paginationParams);
    }

    @Test
    public void createDish_WhenCalledWithValidData_DoesNotReturnException(){
        when(restaurantPersistencePort.findRestaurantById(validDish.getRestaurantId())).thenReturn(Optional.ofNullable(validRestaurant));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userOwner.getId());

        assertDoesNotThrow(() -> dishUseCase.createDish(validDish));
    }

    @Test
    public void createDish_WhenCalledWithRestaurantDoesNotExist_ReturnException(){
        when(restaurantPersistencePort.findRestaurantById(validDish.getRestaurantId())).thenReturn(Optional.empty());
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userOwner.getId());

        assertThrows(RestaurantNotFoundException.class, () -> dishUseCase.createDish(validDish));
    }

    @Test
    public void createDish_WhenCalledWithUserIsNotTheOwner_ReturnException(){
        User userNotOwner = userOwner;
        userNotOwner.setId(userOwner.getId()+1);

        when(restaurantPersistencePort.findRestaurantById(validDish.getRestaurantId())).thenReturn(Optional.ofNullable(validRestaurant));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userNotOwner.getId());

        assertThrows(UnauthorizedAccessException.class, () -> dishUseCase.createDish(validDish));
    }


    @Test
    public void updateDish_WhenCalledWithValidInfo_DoesNotReturnException(){
        Integer newPrice = 200;
        String newDescription = "New Description";

        when(restaurantPersistencePort.findRestaurantById(validDish.getRestaurantId())).thenReturn(Optional.ofNullable(validRestaurant));
        when(dishPersistencePort.findDishById(validDish.getId())).thenReturn(Optional.ofNullable(validDish));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userOwner.getId());

        assertDoesNotThrow(() -> dishUseCase.updateDish(validDish.getId(), newPrice, newDescription));
    }

    @Test
    public void updateDish_WhenCalledByAnUserIsNotTheOwner_ReturnException(){
        User userNotOwnerOfRestaurant = userOwner;
        userNotOwnerOfRestaurant.setId(userOwner.getId() + 1);
        Integer newPrice = 200;
        String newDescription = "New Description";

        when(restaurantPersistencePort.findRestaurantById(validDish.getRestaurantId())).thenReturn(Optional.ofNullable(validRestaurant));
        when(dishPersistencePort.findDishById(validDish.getId())).thenReturn(Optional.ofNullable(validDish));
        when(userAuthenticationPort.getAuthenticatedUserId()).thenReturn(userNotOwnerOfRestaurant.getId());

        assertThrows(UnauthorizedAccessException.class, () -> dishUseCase.updateDish(validDish.getId(), newPrice, newDescription));
    }




}
