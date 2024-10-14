package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.model.Order;

public interface IMessagerConnectionPort {
    void sendNotifySMSOrderReady(String phonerNumber, String message);
}
