package com.playground.auth8.rest.auth;

import com.playground.auth8.exception.BaseException;
import com.playground.auth8.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import com.playground.auth8.service.ClientDetailsService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicAuthProvider implements AuthenticationProvider {

    private final ClientDetailsService clientService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String clientId = authentication.getName();
        String clientSecret = authentication.getCredentials().toString();

        if (!clientService.validate(clientId, clientSecret)) {
            throw new BaseException(
                    ErrorCode.AUTHENTICATION_FAILED,
                    "Invalid client credentials"
            );
        }

        log.debug("authentication granted for client {}", clientId);

        return new UsernamePasswordAuthenticationToken(clientId, null, List.of(new SimpleGrantedAuthority("ROLE_CLIENT")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
