package com.plazoleta.plazoleta.infraestructure.out.auth;

import com.plazoleta.plazoleta.domain.enums.RoleEnum;
import com.plazoleta.plazoleta.domain.model.external.Role;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;

public class UserAuthenticationAdapter implements IUserAuthenticationPort {


    @Override
    public User getAuthenticatedUser() {
        //TODO: Use spring security context holder here
        User user = new User();
        user.setId(13L);
        user.setRole(new Role(RoleEnum.OWNER.getId(), "OWNER"));
        return user;
    }
}
