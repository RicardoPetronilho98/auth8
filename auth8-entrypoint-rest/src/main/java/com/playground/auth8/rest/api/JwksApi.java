package com.playground.auth8.rest.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping
public interface JwksApi {

    @GetMapping(
            path = "/.well-known/jwks.json",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Map<String, Object> keys();

}
