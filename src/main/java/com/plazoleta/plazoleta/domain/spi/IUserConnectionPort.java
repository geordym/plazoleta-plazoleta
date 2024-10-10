package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.model.external.User;

import java.util.Optional;

public interface IUserConnectionPort {

    Optional<User> findUserById(Long userId);
}
