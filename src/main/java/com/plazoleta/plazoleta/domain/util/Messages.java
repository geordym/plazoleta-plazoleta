package com.plazoleta.plazoleta.domain.util;

import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.Restaurant;
import com.plazoleta.plazoleta.domain.model.external.User;

public class Messages {

    public final static String ORDER_READY_MESSAGE_TEMPLATE = "Hola %s, tu pedido con ID %d está listo para ser recogido.\n" +
            "Restaurante: %s\n" +
            "Dirección: %s\n" +
            "Teléfono: %s\n" +
            "Código de reclamación: %d\n" +
            "¡Te esperamos!";

    public static String messageOrderReady(Order order, User user, Integer reclaimCode) {
        String restaurantName = order.getRestaurant().getName();
        String restaurantAddress = order.getRestaurant().getAddress();
        String restaurantPhone = order.getRestaurant().getPhone();

        String clientName = user.getName();

        Long orderId = order.getId();

        return String.format(Messages.ORDER_READY_MESSAGE_TEMPLATE, clientName, orderId, restaurantName, restaurantAddress, restaurantPhone, reclaimCode);
    }


}
