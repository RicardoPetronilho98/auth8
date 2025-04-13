package com.playground.auth8.service;

import com.playground.auth8.dataprovider.ClientDataProvider;
import com.playground.auth8.domain.Client;
import com.playground.auth8.domain.TokenRequest;
import com.playground.auth8.domain.TokenResponse;
import com.playground.auth8.domain.TokenType;
import com.playground.auth8.util.OAuth2Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenIssuer {

    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;
    private final OAuth2Properties OAuth2Properties;
    private final ClientDataProvider dataProvider;

    public TokenResponse issue(TokenRequest tokenRequest) {
        String clientId = getClientId();
        Jwt idpToken = decodeJwt(tokenRequest);
        Client client = dataProvider.findById(clientId);
        String newToken = encodeJwt(idpToken, client);
        return TokenResponse.builder()
                .issuedTokenType(TokenType.ACCESS_TOKEN.getValue())
                .tokenType(TokenType.BEARER.getValue())
                .expiresIn(OAuth2Properties.getToken().getAccess().getTtl().toSeconds())
                .accessToken(newToken)
                .refreshToken(null) // TODO
                .scope(client.getScopes())
                .audience(List.of(OAuth2Properties.getToken().getClaims().getAudience()))
                .build();
    }

    private Jwt decodeJwt(TokenRequest tokenRequest) {
        try {
            return jwtDecoder.decode(tokenRequest.subjectToken());
        } catch (JwtException e) {
            throw new RuntimeException(); // TODO
        }
    }

    private String encodeJwt(Jwt idpToken, Client client) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(OAuth2Properties.getToken().getClaims().getIssuer())
                .subject(idpToken.getSubject())
                .audience(List.of(OAuth2Properties.getToken().getClaims().getAudience()))
                .issuedAt(now)
                .expiresAt(now.plus(OAuth2Properties.getToken().getAccess().getTtl()))
                .claim("scope", String.join(" ", client.getScopes()))
                .claim("jti", UUID.randomUUID().toString())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private static String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken token) {
            Object principal = token.getPrincipal();
            if (principal instanceof String clientId) {
                return clientId;
            }
        }
        throw new RuntimeException("could not access authenticated client"); // TODO
    }

}
