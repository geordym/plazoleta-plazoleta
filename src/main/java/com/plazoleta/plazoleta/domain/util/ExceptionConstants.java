package com.plazoleta.plazoleta.domain.util;

public class ExceptionConstants {

    public static final String INVALID_USER_ROLE_ERROR = "Invalid user role";
    public static final String INVALID_USER_ROLE_MESSAGE = "The user you want to add to create an restaurant does not is a owner";

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,13}$";

    public static final String NUMBER_PREFIX = "+";

    public static final String PHONE_NUMBER_ERROR = "Invalid phone number.";
    public static final String PHONE_NUMBER_MESSAGE = "Please enter a valid phone number this must start with an '+' and max length must be 13 digits.";

    public static final String INVALID_RESTAURANT_NAME_ERROR = "INVALID_RESTAURANT_NAME";
    public static final String INVALID_RESTAURANT_NAME_MESSAGE = "Restaurant name cannot consist only of numbers.";


}

