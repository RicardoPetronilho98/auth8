package com.playground.auth8.rest.controller;

import com.playground.auth8.domain.TokenResponse;
import com.playground.auth8.rest.api.TokenGranterApi;
import com.playground.auth8.domain.TokenRequest;
import com.playground.auth8.rest.validator.TokenRequestValidator;
import com.playground.auth8.service.TokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TokenGranter implements TokenGranterApi {

    private final TokenIssuer issuer;

    public ResponseEntity<TokenResponse> exchange(Map<String, String> params) {
        TokenRequest tokenRequest = TokenRequestValidator.validateAndRetrieve(params);
        TokenResponse token = issuer.issue(tokenRequest);
        return ResponseEntity.ok(token);
    }

}