package com.plazoleta.plazoleta.domain.spi;

import com.plazoleta.plazoleta.domain.model.external.Role;
import com.plazoleta.plazoleta.domain.model.external.User;

public interface IUserAuthenticationPort {
    User getAuthenticatedUser();
    Long getAuthenticatedUserId();
    Role getAuthenticatedUserRole();
}
