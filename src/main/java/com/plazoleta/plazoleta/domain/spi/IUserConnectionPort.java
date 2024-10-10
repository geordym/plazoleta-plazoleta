package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.model.external.User;

public interface IUserConnectionPort {

    User findUserById(Long userId);
}
