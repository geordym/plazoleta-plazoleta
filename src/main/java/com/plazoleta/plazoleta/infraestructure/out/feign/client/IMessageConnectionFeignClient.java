package com.plazoleta.plazoleta.infraestructure.out.feign.client;


import com.plazoleta.plazoleta.domain.model.external.Employee;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.infraestructure.out.feign.configuration.FeignClientConfig;
import com.plazoleta.plazoleta.infraestructure.out.feign.model.MessageRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MESSAGE-API", url = "${API_MESSAGE_URL}", configuration = FeignClientConfig.class)
public interface IMessageConnectionFeignClient {

    @PostMapping(value = "/api/message/sms", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendMessageSMS(@RequestBody MessageRequestDto messageRequestDto);


}
