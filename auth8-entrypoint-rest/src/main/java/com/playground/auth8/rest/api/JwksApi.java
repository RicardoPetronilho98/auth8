package com.playground.auth8.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping
public interface JwksApi {

    @GetMapping("/.well-known/jwks.json")
    Map<String, Object> keys();

}
