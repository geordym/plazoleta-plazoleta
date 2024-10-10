package com.plazoleta.plazoleta.infraestructure.out.feign.client;


import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.infraestructure.out.feign.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-API", url = "${external.user.api.base-url}", configuration = FeignClientConfig.class)
public interface IUserConnectionFeignClient {

    @GetMapping(value = "/api/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    User findClientById(@PathVariable Long userId);


}
