package com.plazoleta.plazoleta.infraestructure.out.feign.client;


import com.plazoleta.plazoleta.domain.model.external.OrderLog;
import com.plazoleta.plazoleta.infraestructure.out.feign.configuration.FeignClientConfig;
import com.plazoleta.plazoleta.infraestructure.out.feign.model.MessageRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TRACEABILITY-API", url = "${API_TRACEABILITY_URL}", configuration = FeignClientConfig.class)
public interface ITraceabilityConnectionFeignClient {

    @PostMapping(value = "/api/order-log", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendOrderLog(@RequestBody OrderLog orderLog);


}
