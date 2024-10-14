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

    public static final String EXTERNAL_CONNECTION_ERROR = "External connection error";
    public static final String EXTERNAL_CONNECTION_MESSAGE = "We cannot connect with an required external service please try more later.";

    public static final String USER_DOESNOT_EXIST_ERROR = "User not found";
    public static final String USER_DOESNOT_EXIST_MESSAGE = "We cannot found the user do you want to use.";

    public static final String UNAUTHORIZATED_ACCESS_ERROR = "Unauthorized access error";
    public static final String UNAUTHORIZATED_ACCESS_ERROR_MESSAGE = "You cannot access to this resource, that can be because you have not permission to modify that restaurant.";

    public static final String RESTAURANT_NOT_FOUND_ERROR = "The restaurant cannot be founded";
    public static final String RESTAURANT_NOT_FOUND_ERROR_MESSAGE = "That restaurant cannot be founded.";

    public static final String DISH_NOT_FOUND_ERROR = "The dish cannot be founded";
    public static final String DISH_NOT_FOUND_ERROR_MESSAGE = "That dish cannot be founded.";
    public static final String DISH_NOT_FOUND_IN_RESTAURANT_MESSAGE = "Dish ID %d does not exist in restaurant ID %d";

    public static final String ORDER_IN_PROGRESS_ERROR = "You have an order in process already check it";
    public static final String ORDER_IN_PROGRESS_MESSAGE = "You have a order in process id %d and restaurant id %d";


    public static final String ORDER_NOT_FOUND_ERROR = "We cannot find the order";
    public static final String ORDER_NOT_FOUND_MESSAGE = "The order do you wanna to assign, does not exist";

    public static final String ORDER_NOT_PENDING_ERROR = "Order is not in status pending";
    public static final String ORDER_NOT_PENDING_MESSAGE = "The order do you wanna to assign not have the status pending";

    public static final String ORDER_NOT_PREPARING_ERROR = "Order is not in status preparing";
    public static final String ORDER_NOT_PREPARING_MESSAGE = "The order do you wanna to notify as ready is not in preparing status";

}

