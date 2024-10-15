package com.plazoleta.plazoleta.infraestructure.out.feign.adapter;

import com.plazoleta.plazoleta.domain.model.external.OrderLog;
import com.plazoleta.plazoleta.domain.spi.ITraceabilityConnectionPort;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.ITraceabilityConnectionFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceabiltyConnectionFeignAdapter implements ITraceabilityConnectionPort {
    private final ITraceabilityConnectionFeignClient traceabilityConnectionFeignClient;

    @Override
    public void registerOrderLog(OrderLog orderLog) {
        traceabilityConnectionFeignClient.sendOrderLog(orderLog);
    }
}
