package com.plazoleta.plazoleta.domain.enums;


public enum DishEnumSortBy {
    NAME("name", "name"),
    CATEGORY("category", "category" ),
    PRICE("price", "price");

    private final String value;
    private final String entityAttribute;

    DishEnumSortBy(String value, String entityAttribute) {
        this.value = value;
        this.entityAttribute = entityAttribute;
    }

    public String getValue() {
        return value;
    }

    public String entityAttribute() {
        return entityAttribute;
    }

    public static DishEnumSortBy fromValue(String value) {
        for (DishEnumSortBy sortBy : DishEnumSortBy.values()) {
            if (sortBy.getValue().equalsIgnoreCase(value)) {
                return sortBy;
            }
        }

        throw new IllegalArgumentException("Invalid sortBy value: " + value);
    }
}

