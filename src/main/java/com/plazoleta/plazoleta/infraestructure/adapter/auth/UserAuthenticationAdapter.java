package com.plazoleta.plazoleta.infraestructure.adapter.auth;

import com.plazoleta.plazoleta.domain.enums.RoleEnum;
import com.plazoleta.plazoleta.domain.model.external.Role;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.infraestructure.configuration.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class UserAuthenticationAdapter implements IUserAuthenticationPort {

    private final IUserConnectionPort userConnectionPort;

    @Override
    public User getAuthenticatedUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userConnectionPort.findUserById(Long.valueOf(customUserDetails.getUserId())).orElseThrow();
    }

    @Override
    public Long getAuthenticatedUserId() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Long.valueOf(customUserDetails.getUserId());
    }

    @Override
    public Role getAuthenticatedUserRole() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Obtener la primera autoridad
        String firstAuthority = customUserDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);

        return RoleEnum.fromName(firstAuthority).toModel();
    }



}
