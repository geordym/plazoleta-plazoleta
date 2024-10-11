package com.plazoleta.plazoleta.domain.enums;


public enum RestaurantSortBy {
    NAME("name", "name"),
    NIT("nit", "nit" ),
    ADDRESS("address", "address");

    private final String value;
    private final String entityAttribute;

    RestaurantSortBy(String value, String entityAttribute) {
        this.value = value;
        this.entityAttribute = entityAttribute;
    }

    public String getValue() {
        return value;
    }

    public String entityAttribute() {
        return entityAttribute;
    }

    public static RestaurantSortBy fromValue(String value) {
        for (RestaurantSortBy sortBy : RestaurantSortBy.values()) {
            if (sortBy.getValue().equalsIgnoreCase(value)) {
                return sortBy;
            }
        }

        throw new IllegalArgumentException("Invalid sortBy value: " + value);
    }
}

