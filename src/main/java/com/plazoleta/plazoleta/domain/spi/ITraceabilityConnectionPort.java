package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.model.external.OrderLog;

public interface ITraceabilityConnectionPort {

    void registerOrderLog(OrderLog orderLog);
}
