package com.plazoleta.plazoleta.infraestructure.out.feign.adapter;

import com.plazoleta.plazoleta.domain.exception.ExternalConnectionException;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.IUserConnectionFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserConnectionFeignAdapter implements IUserConnectionPort {

    private final IUserConnectionFeignClient IUserConnectionFeignClient;

    @Override
    public Optional<User> findUserById(Long userId) {
        try {
            User user = IUserConnectionFeignClient.findClientById(userId);
            return Optional.ofNullable(user);
        } catch (FeignException.NotFound ex) {
            return Optional.empty();
        } catch (Exception ex) {
            throw new ExternalConnectionException();
        }
    }



}
