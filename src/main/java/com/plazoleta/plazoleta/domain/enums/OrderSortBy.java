package com.plazoleta.plazoleta.domain.enums;

public enum OrderSortBy {
    STATUS("status", "status");


    private final String value;
    private final String entityAttribute;

    OrderSortBy(String value, String entityAttribute) {
        this.value = value;
        this.entityAttribute = entityAttribute;
    }

    public String getValue() {
        return value;
    }

    public String entityAttribute() {
        return entityAttribute;
    }

    public static OrderSortBy fromValue(String value) {
        for (OrderSortBy sortBy : OrderSortBy.values()) {
            if (sortBy.getValue().equalsIgnoreCase(value)) {
                return sortBy;
            }
        }
        throw new IllegalArgumentException("Invalid sortBy value: " + value);
    }

}
