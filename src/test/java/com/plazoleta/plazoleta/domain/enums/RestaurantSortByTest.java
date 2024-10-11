package com.plazoleta.plazoleta.domain.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class RestaurantSortByTest {
    @Test
    public void testGetValue() {
        assertThat(RestaurantSortBy.NAME.getValue()).isEqualTo("name");
        assertThat(RestaurantSortBy.NIT.getValue()).isEqualTo("nit");
        assertThat(RestaurantSortBy.ADDRESS.getValue()).isEqualTo("address");
    }

    @Test
    public void testEntityAttribute() {
        assertThat(RestaurantSortBy.NAME.entityAttribute()).isEqualTo("name");
        assertThat(RestaurantSortBy.NIT.entityAttribute()).isEqualTo("nit");
        assertThat(RestaurantSortBy.ADDRESS.entityAttribute()).isEqualTo("address");
    }

    @Test
    public void testFromValue_ValidValue() {
        assertThat(RestaurantSortBy.fromValue("name")).isEqualTo(RestaurantSortBy.NAME);
        assertThat(RestaurantSortBy.fromValue("nit")).isEqualTo(RestaurantSortBy.NIT);
        assertThat(RestaurantSortBy.fromValue("address")).isEqualTo(RestaurantSortBy.ADDRESS);
    }

    @Test
    public void testFromValue_InvalidValue() {
        assertThatThrownBy(() -> RestaurantSortBy.fromValue("invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid sortBy value: invalid");
    }

}
