package com.playground.auth8.domain;


public record TokenRequest(String grantType, String subjectTokenType, String subjectToken) {

}
