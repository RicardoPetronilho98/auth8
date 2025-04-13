package com.playground.auth8.rest.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.playground.auth8.rest.api.JwksApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwksController implements JwksApi {

    private final JWKSet jwks;

    public JwksController(@Qualifier("publicJwkSet") JWKSet jwks) {
        this.jwks = jwks;
    }

    public Map<String, Object> keys() {
        return this.jwks.toJSONObject();
    }

}
