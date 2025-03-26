package com.playground.auth8.rest.validator;

import com.playground.auth8.domain.TokenType;
import com.playground.auth8.domain.TokenRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.StringUtils;

import java.util.Map;

public class TokenRequestValidator {

    public static TokenRequest validateAndRetrieve(Map<String, String> params) {
        String grantType = params.get("grant_type");
        if (!AuthorizationGrantType.TOKEN_EXCHANGE.getValue().equals(grantType)) {
            throw new RuntimeException(); // TODO
        }

        String subjectTokenType = params.get("subject_token_type");
        if (!TokenType.ACCESS_TOKEN.getValue().equals(subjectTokenType)) {
            throw new RuntimeException(); // TODO
        }

        String subjectToken = params.get("subject_token");
        if (!StringUtils.hasText(subjectToken)) {
            throw new RuntimeException(); // TODO
        }

        return new TokenRequest(grantType, subjectTokenType, subjectToken);
    }

}
