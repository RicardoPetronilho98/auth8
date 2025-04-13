package com.playground.auth8.rest.validator;

import com.playground.auth8.domain.TokenType;
import com.playground.auth8.domain.TokenRequest;
import com.playground.auth8.exception.BaseException;
import com.playground.auth8.exception.ErrorCode;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.StringUtils;

import java.util.Map;

public class TokenRequestValidator {

    private static final String GRANT_TYPE = "grant_type";
    private static final String SUBJECT_TOKEN_TYPE = "subject_token_type";
    private static final String SUBJECT_TOKEN = "subject_token";

    public static TokenRequest validateAndRetrieve(Map<String, String> params) {
        String grantType = params.get(GRANT_TYPE);
        if (!AuthorizationGrantType.TOKEN_EXCHANGE.getValue().equals(grantType)) {
            throw new BaseException(
                    ErrorCode.BAD_REQUEST,
                    String.format("Invalid %s", GRANT_TYPE)
            );
        }

        String subjectTokenType = params.get(SUBJECT_TOKEN_TYPE);
        if (!TokenType.ACCESS_TOKEN.getValue().equals(subjectTokenType)) {
            throw new BaseException(
                    ErrorCode.BAD_REQUEST,
                    String.format("Invalid %s", SUBJECT_TOKEN_TYPE)
            );
        }

        String subjectToken = params.get("subject_token");
        if (!StringUtils.hasText(subjectToken)) {
            throw new BaseException(
                    ErrorCode.BAD_REQUEST,
                    String.format("Invalid %s", SUBJECT_TOKEN)
            );
        }

        return new TokenRequest(grantType, subjectTokenType, subjectToken);
    }

}
