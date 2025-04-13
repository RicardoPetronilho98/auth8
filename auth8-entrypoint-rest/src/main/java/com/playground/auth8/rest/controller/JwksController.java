package com.playground.auth8.rest.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.playground.auth8.rest.api.JwksApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwksController implements JwksApi {

    private final JWKSet jwkSet;

    public Map<String, Object> keys() {
        return this.jwkSet.toJSONObject();
    }

}
