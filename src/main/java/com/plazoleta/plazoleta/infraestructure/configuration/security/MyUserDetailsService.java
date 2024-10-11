package com.plazoleta.plazoleta.infraestructure.configuration.security;


import com.plazoleta.plazoleta.domain.spi.security.ITokenProviderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final ITokenProviderPort tokenProviderPort;
    private final String USERNAME_NOT_FOUND_MESSAGE = "The username has been not found";
    @Override
    public UserDetails loadUserByUsername(String token){
        String subject = tokenProviderPort.extractSubject(token);
        String role = tokenProviderPort.extractRole(token);

        if (subject != null && !subject.isEmpty()) {
            Collection<GrantedAuthority> authorities = Arrays.asList(
                    new SimpleGrantedAuthority(role)
            );
            CustomUserDetails userDetails = new CustomUserDetails(subject, token, authorities, true);
            return userDetails;
        } else {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND_MESSAGE);
        }
    }
}
