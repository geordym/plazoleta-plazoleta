package com.plazoleta.plazoleta.domain.usecase;


import com.plazoleta.plazoleta.domain.enums.RoleEnum;
import com.plazoleta.plazoleta.domain.exception.InvalidPhoneNumberException;
import com.plazoleta.plazoleta.domain.exception.InvalidRestaurantNameException;
import com.plazoleta.plazoleta.domain.exception.UserIsNotRestaurantOwnerException;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.Role;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class RestaurantUseCaseTest {


    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserConnectionPort userConnectionPort;

    private RestaurantUseCase restaurantUseCase;

    private Restaurant validRestaurant;

    private User userOwner;


    @BeforeEach
    void setup(){
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, userConnectionPort);

        validRestaurant = new Restaurant("Pizza Pro", 99999L, "Esquina 49 diagonal 9", "+573026468094", "logo.png", 1L);

        Role role = new Role(RoleEnum.OWNER.getId(), RoleEnum.OWNER.getNameBd());
        userOwner = new User(role);
    }

    @Test
    public void createRestaurant_WhenCalledWithValidData_DoesNotReturnException(){

        when(userConnectionPort.findUserById(validRestaurant.getOwnerId())).thenReturn(Optional.ofNullable(userOwner));

        assertDoesNotThrow(() -> restaurantUseCase.createRestaurant(validRestaurant));
    }


    static Stream<String> invalidPhoneNumbers() {
        return Stream.of(
                "573026468094",    // Sin +
                "12345678901234",  // Más de 13 dígitos
                "+",                // Solo el símbolo +
                "+12345678901234", // Más de 13 dígitos con +
                "invalidPhone"     // No es numérico
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPhoneNumbers")
    public void createOwner_WhenCalledWithInvalidPhoneNumber_ThrowsException(String invalidPhone) {
        Restaurant invalidRestaurant = validRestaurant;
        invalidRestaurant.setPhone(invalidPhone);

        assertThrows(InvalidPhoneNumberException.class, () -> restaurantUseCase.createRestaurant(invalidRestaurant));
    }

    @Test
    public void createRestaurant_WhenCalledWithUserIsNotOwner_ReturnException(){
        User userNotOwner = userOwner;
        userNotOwner.setRole(new Role(RoleEnum.ADMINISTRATOR.getId(), RoleEnum.ADMINISTRATOR.getName()));

        when(userConnectionPort.findUserById(validRestaurant.getOwnerId())).thenReturn(Optional.of(userNotOwner));

        assertThrows(UserIsNotRestaurantOwnerException.class, () -> restaurantUseCase.createRestaurant(validRestaurant));
    }

    @Test
    public void createRestaurant_WhenCalledWithInvalidRestaurantName_ReturnException(){
        Restaurant invalidRestaurant = validRestaurant;
        invalidRestaurant.setName("999999999");
        assertThrows(InvalidRestaurantNameException.class, () -> restaurantUseCase.createRestaurant(validRestaurant));
    }

}
